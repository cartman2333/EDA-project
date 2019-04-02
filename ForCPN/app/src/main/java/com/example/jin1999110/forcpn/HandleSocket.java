package com.example.jin1999110.forcpn;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static com.example.jin1999110.forcpn.FrameHelper.reverse;

public class HandleSocket {
    private static String info ="HandleSocket";
    public void server(int port,Message message) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress socketAddress = new InetSocketAddress( port);
        serverSocket.bind(socketAddress);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ){
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel client = channel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                        Log.d(info,"Accepted connection from " + client);
                    } else if (key.isReadable()) {
                        StringBuilder sb = new StringBuilder();
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

                        int byteRead = client.read(byteBuffer);
                        if (byteRead == -1) {
                            key.cancel();
                            client.close();
                        }
                        String temp = null;
                        while (byteRead > 0) {
                            Log.d(info,"Read " + byteRead);
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteRead];
                            byteBuffer.get(bytes);
                            byteBuffer.clear();
                            byteRead = client.read(byteBuffer);
                            for (byte b : bytes) {
                                temp = Integer.toHexString(0xff & b);
                                if (temp.length() == 1) {
                                    temp = "0" + temp;
                                }
                                sb.append(temp);
                            }
                        }
                        message.what=1;
                        Bundle bundle = new Bundle();
                        bundle.putString("text",sb.toString().toUpperCase());
                        message.setData(bundle);


                        message.sendToTarget();

                        Message message1=new Message();
                        message1.setTarget(message.getTarget());
                        message=new Message();
                        message.setTarget(message1.getTarget());
                        handleData(sb.toString(),client,message1);

                        //message=message.getTarget().obtainMessage();


                    }

                } catch (IOException e) {
                    key.cancel();
                }
            }
        }
    }

    private void handleData(String data,SocketChannel client,Message message){

        int [] bytes = new int[data.length()/2];
        int [] ackResult = new int[4];
        for(int i = 0; i < data.length() / 2; i++) {
            String subStr = data.substring(i * 2, i * 2 + 2);
            bytes[i] = Integer.parseInt(subStr, 16);
            if(i>=12&&i<=13){
                ackResult[i-12]=bytes[i];
            }
            if(i>=16&&i<=17){
                ackResult[i-14]=bytes[i];
            }
        }

        FramePo ackPo = FrameHelper.getACKFramePo(ackResult);
        message.what=2;
        Bundle bundle1 = new Bundle();
        bundle1.putString("text",ackPo.getString());
        message.setData(bundle1);
        message.sendToTarget();
        message=message.getTarget().obtainMessage();
        //Log.d(info, bytes[96]+"  "+bytes[18]+" "+bytes[97]);
        if (bytes[18]==3) {
            System.out.println("设定："+data);
            FramePo setPo1 = FrameHelper.getSetResultFramePo1(bytes);
            String s=setPo1.getString();

            message.what=2;
            Bundle bundle2 = new Bundle();
            bundle2.putString("text",s);
            message.setData(bundle2);
            message.sendToTarget();
            message=message.getTarget().obtainMessage();
            try {
                client.write(ByteBuffer.wrap(ackPo.getBytes()));
                client.write(ByteBuffer.wrap(setPo1.getBytes()));
                //Log.d(info, "handleData: "+s+"buchengg"+(s.substring(s.length()-7,s.length()-6).equals("1")));
                if (s.substring(s.length()-7,s.length()-6).equals("1")){
                    Log.d(info, "handleData: "+"成功");
                    FramePo setPo2 = FrameHelper.getSetResultFramePo2(bytes);
                    message.what=2;
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("text",setPo2.getString());
                    message.setData(bundle3);
                    message.sendToTarget();
                    //message=message.getTarget().obtainMessage();
                    client.write(ByteBuffer.wrap(setPo2.getBytes()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (bytes[18]==2){
            System.out.println("查询"+data);
            try {
                FramePo setPo3=FrameHelper.getQueryFramePo(bytes);

                message.what=2;
                Bundle bundle4=new Bundle();
                bundle4.putString("text",setPo3.getString());
                message.setData(bundle4);
                message.sendToTarget();

                client.write(ByteBuffer.wrap(ackPo.getBytes()));
                client.write(ByteBuffer.wrap(setPo3.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    public static String getIP(Context context){

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex){
            ex.printStackTrace();
        }
        return null;
    }


}
