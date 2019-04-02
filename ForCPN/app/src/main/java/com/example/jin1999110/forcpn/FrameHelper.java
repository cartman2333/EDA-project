package com.example.jin1999110.forcpn;


import android.nfc.Tag;
import android.util.Log;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * ProjectName: NettyInAction
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-12-28 22:14
 **/
public class FrameHelper {
    private static String t1="FrameHelper";
    private static Random random = new Random();
    private static int[] ackFrame = new int[]{
            0x55, 0xFF, 0x01, 0x00, 0x06, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x6E, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00, 0x74, 0x64, 0x01, 0x00
    };
    private static int[] setResultFrame2 = new int[]{
            0x55, 0xFF, 0x09, 0x00, 0x30, 0x00, 0x25, 0x46, 0x32, 0x44, 0x00, 0x00, 0x22, 0x00, 0x01, 0x00, 0x01, 0x00,
            0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBB, 0x19, 0x64, 0x03,
            0x00, 0x00, 0x01, 0x00, 0x11, 0x03, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x05, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x49, 0x19, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00
    };
    private static int[] queryFrame = new int[]{
            0x55, 0xFF, 0x09, 0x00, 0xC0, 0x00, 0x92, 0x59, 0xD1, 0x2D, 0x00, 0x00, 0x0A, 0x00, 0x01, 0x00, 0x01, 0x00,
            // App名称4，任务名称4，事件名称4,需要从查询触发帧获取
            0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x68, 0xC3, 0x17, 0x0D,
            // 返回的变量个数2，需要从查询触发帧获取，本例0003，3个变量
            // 返回的查询码名称4，000A，返回的查询码长度4，查询码类型2 保留
            0x00, 0x00, 0x03, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x05, 0x00, 0x00,
            0x0B, 0x00, 0x00, 0x00, 0x10, 0x00, 0x00, 0x00, 0x08, 0x05, 0x00, 0x00};
    private static int packetName = 0x1;

    private static int[] setResultFrame = new int[]{
            0x55, 0xFF, 0x09, 0x00, 0x50, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x23, 0x00, 0x01, 0x00, 0x01, 0x00, 0x03, 0x00, 0x00, 0x80, 0xFF, 0xFF, 0xFF, 0xFF, 0xBB, 0x19, 0x64, 0x03, 0x00, 0x00, 0x02, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x05, 0x00, 0x00, 0x50, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x05, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x4B, 0x19, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x4B, 0x19, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00,
    };

    public static FramePo getSetResultFramePo1(int[] all) {
        LinkedList<Integer> list = new LinkedList<>();
        addList(setResultFrame, list, 0, setResultFrame.length);
        for (int i = 0; i < 4; i++) {
            list.set(i + 26, getRandom());
        }

        list.set(42, 0x00);
        list.set(43, 0x05);

        // 执行结果变量名称
        list.set(46, 0x50);
        list.set(47, 0x00);
        list.set(47, 0x00);
        list.set(47, 0x00);

        // 设置第一个变量的注册状态
        list.set(68, 0x01);
        list.set(69, 0x00);
        // 设置更新时间 70 71 72 73
        list.set(70, 0x00);
        list.set(71, 0x00);
        list.set(72, 0x00);
        list.set(73, 0x00);

        setList(all, list, 74, 118, 4);

        list.set(88, 0x01);
        list.set(89, 0x00);

        list.set(90, 0x00);
        list.set(91, 0x00);
        list.set(92, 0x00);
        list.set(93, 0x00);

        list.set(94, 0x01);
        list.set(95, 0x00);
        list.set(96, 0x00);
        list.set(97, 0x00);
        int num = getNum(all[182], all[183],all[184],all[185]);
        //Log.d(t1, "getSetResultFramePo1: "+num);
        for (int i = 0; i < num; i++) {
            int[] name = new int[]{
                    all[187 + 12 * i], all[188 + 12 * i], all[189 + 12 * i], all[190 + 12 * i]
            };
            String r=reverse(name,0,name.length);
            Log.d(t1, r);
            if(!LitePal.isExist(register.class,"address = ?",r)){
                Log.d(t1, "notExist");
                list.set(88,0x00);
                list.set(94,0x00);
            }
        }
        // 设置注册状态，88，89

        // 设置更新时间 90，91，92，93

        // 设置执行成功的标志，94,95,96,97 0x00000001 表示成功，0x00000000表示失败


        return transToFrame(list);
    }

    public static FramePo getSetResultFramePo2(int[] src) {
        List<Integer> list = new LinkedList<>();
        addList(setResultFrame2, list, 0, 32);
        for (int i = 0; i < 4; i++) {
            list.set(i + 26, getRandom());
        }
        int num = getNum(src[182], src[183],src[184],src[185]);
        // 设置变量个数 32 33
        addList(getArray(num, 2), list, 0, 2);

        for (int i = 0; i < num; i++) {
            int[] name = new int[]{
                    src[186 + 12 * i], src[187 + 12 * i], src[188 + 12 * i], src[189 + 12 * i]
            };
            // 变量名称
            addList(name, list, 0, 4);
            // 变量长度
            list.add(0x04);
            add0x00(list, 3);
            // 变量类型，此处设为0x0005
            list.add(0x05);
            list.add(0x00);
            list.add(0x00);
            list.add(0x00);
            list.add(0x00);
            list.add(0x00);
        }
        for (int i = 0; i < num; i++) {
            int[] value = new int[]{
                    src[194 + 12 * i], src[195 + 12 * i], src[196 + 12 * i], src[197 + 12 * i]
            };
            // 领域指示状态
            list.add(0x01);
            add0x00(list, 3);
            // 字节长度
            list.add(0x04);
            add0x00(list, 3);

            // 计算步骤
            list.add(0x01);
            list.add(0x00);

            // 注册状态
            list.add(0x01);
            list.add(0x00);

            // 更新时间
            add0x00(list, 4);
            addList(value, list, 0, 4);
        }
        //
        setList(getArray(list.size() - 18, 2), list, 4, 0, 2);
        return transToFrame(list);
    }

    public static FramePo getQueryFramePo(int[] all) throws Exception {
        int length = all.length;
        List<Integer> queryList = new LinkedList<>();
        // flag == 2 需要返回节点码 flag == 1 不需要返回
        int flag = getNum(all[180], all[181]) + 1;
        int needImport = 46;
        if (flag == 2) {
            needImport = 58;
        }
        for (int i = 0; i < needImport; i++) {
            queryList.add(queryFrame[i]);
        }

        int shouldLength = getNum(all[4], all[5]);
        if (length != shouldLength + 18) {
            throw new Exception("帧不全");
        }

        int[] packetName = getPacketName();
        queryList.set(12, packetName[0]);
        queryList.set(13, packetName[1]);
        int vLength = getNum(all[158], all[159]);
        int vNum = (vLength - 20) / 4;

        setList(all, queryList, 18, 165, 8);
        for (int i = 0; i < 4; i++) {
            queryList.set(i + 26, getRandom());
        }
        // 获取节点数量
        int num = 1;
        // 返回变量的数量
        setList(getArray(vNum + flag, 2), queryList, 32, 0, 2);
        for (int i = vNum; i > 0; i--) {

            addList(all, queryList, vLength - i * 4, vLength - (i - 1) * 4);
            // 查询变量的长度，后面进行修改
            addList(getArray(16 * num, 4), queryList, 0, 4);
            queryList.add(0x08);
            queryList.add(0x05);
            queryList.add(0x00);
            queryList.add(0x00);
        }
        // 邻域指示状态
        addNextState(queryList);
        // 查询码长度
        queryList.add(0x04);
        add0x00(queryList, 3);

        // 计算步骤
        queryList.add(0x01);
        queryList.add(0x00);
        // 增加6B，数据注册状态，数据更新时间
        add0x00(queryList, 6);
        addList(all, queryList, 118, 122);
        if (flag == 2) {
            addNextState(queryList);
            // 返回的区域码长度，由节点的数量确定 28*n
            add0x00(queryList, 4);
            // 计算步骤
            queryList.add(0x01);
            queryList.add(0x00);
            // 增加6B的，数据注册状态，数据更新时间
            add0x00(queryList, 6);
            for (int i = 0; i < num; i++) {
                // 节点名称长度暂时固定为16
                queryList.add(0x10);
                add0x00(queryList, 3);
                // 计算步骤
                queryList.add(0x01);
                queryList.add(0x00);
                // 增加6B的，数据注册状态 2B，数据更新时间4B
                add0x00(queryList, 6);
                // 增加16B的节点名称,改
                add0x00(queryList, 16);
            }
        }
        for (int i = vNum; i > 0; i--) {
            // 变量3的领域指示标志
            addNextState(queryList);
            // 查询变量长度，和上述相同
            addList(getArray(16 * num, 4), queryList, 0, 4);
            // 计算步骤
            queryList.add(0x01);
            queryList.add(0x00);
            // 增加6B的，数据注册状态，数据更新时间
            add0x00(queryList, 6);
            // num 是 节点的数量
            for (int k = 0; k < num; k++) {
                queryList.add((0x01));
                add0x00(queryList, 3);
                // 计算步骤
                queryList.add(0x01);
                queryList.add(0x00);
                // 增加6B的，数据注册状态，数据更新时间
                add0x00(queryList, 6);
                Log.d("FrameHelper", "getQueryFramePo: "+vLength+" "+i);
                // 变量名称
                String rName = reverse(all, (vLength - 4 * i), (vLength - 4 * (i - 1)));
                // 获取变量值，正序变量，请修改
                String data = rName;
                int[] temp = new int[4];
                reverse(data, temp);
                addList(temp, queryList, 0, 4);
            }
        }
        setList(getArray(queryList.size() - 18, 2), queryList, 4, 0, 2);
        return transToFrame(queryList);
    }

    // 7800AC-> {0xAC, 0x00, 0x78}
    public static void reverse(String v, int[] temp) {
        for (int i = 0; i < v.length() / 2; i++) {
            String subStr = v.substring(v.length() - i * 2 - 2, v.length() - i * 2);
            temp[i] = Integer.parseInt(subStr, 16);
        }
    }

    private static String reverse(int[] src, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = end - 1; i >= start; i--) {
            String s = Integer.toHexString(src[i]);
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    private static void addList(int[] src, List<Integer> des, int start, int end) {

        for (int i = start; i < end; i++) {
            des.add(src[i]);
        }

    }

    private static void setList(int[] src, List<Integer> des, int desStart, int srcStart, int length) {
        for (int i = srcStart, j = desStart; j < desStart + length; i++, j++) {
            des.set(j, src[i]);
        }


    }

    private static int getNum(int... res) {
        int result = 0;
        for (int i = res.length - 1; i >= 0; i--) {
            result += res[i] * Math.pow(256, i);
        }
        return result;
    }

    private static int[] getArray(int num, int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (num % Math.pow(256, i + 1));
            num -= array[i];
        }
        return array;
    }

    private static int getRandom() {
        return random.nextInt(256);
    }

    public static void main(String[] args) {
        System.out.println(getNum(0x31, 0x00, 0x30, 0x00));
    }

    public static FramePo getACKFramePo(int[] result) {
        byte[] frame = new byte[ackFrame.length];
        int[] ack = new int[ackFrame.length];
        System.arraycopy(ackFrame, 0, ack, 0, ackFrame.length);
        int[] packetName = getPacketName();
        ack[12] = packetName[0];
        ack[13] = packetName[1];
        System.arraycopy(result, 0, ack, 20, 4);
        int[] crc = crcCheck(ack, ack.length);
        ack[10] = crc[0];
        ack[11] = crc[1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ack.length; i++) {
            int tmp = ack[i];
            getFrame(frame, sb, i, tmp);
        }
        return new FramePo(sb.toString().toUpperCase(), frame);
    }

    private static void getFrame(byte[] frame, StringBuilder sb, int i, int tmp) {
        String s = Integer.toHexString(tmp);
        if (s.length() == 1) {
            s = "0" + s;
        }
        sb.append(s);
        frame[i] = (byte) tmp;
    }

    public synchronized static int[] getPacketName() {
        int newPacketName = packetName + 0x01;
        if (newPacketName > 0xC350) {
            newPacketName = 0x0001;
        }
        int[] tmp = new int[2];
        for (int i = 0; i < 2; i++) {
            tmp[i] = packetName & 0xFF;
            packetName = packetName >> 8;

        }
        packetName = newPacketName;
        return tmp;
    }

    public static int[] crcCheck(int[] frame, int length) {
        int crcHi = 0xFF;
        int crcLo = 0xFF;
        int index;
        int[] result = new int[2];
        for (int i = 2; i < length; i++) {
            index = crcLo ^ frame[i];
            crcLo = crcHi ^ crcTableLow[index];
            crcHi = crcTableHigh[index];
        }
        result[0] = crcLo;
        result[1] = crcHi;
        return result;
    }

    public static int[] crcCheck(List<Integer> frame, int length) {
        int crcHi = 0xFF;
        int crcLo = 0xFF;
        int index;
        int[] result = new int[2];
        for (int i = 2; i < length; i++) {
            index = crcLo ^ frame.get(i);
            crcLo = crcHi ^ crcTableLow[index];
            crcHi = crcTableHigh[index];
        }
        result[0] = crcLo;
        result[1] = crcHi;
        return result;
    }


    // 增加领域指示状态
    private static void addNextState(List<Integer> list) {
        list.add(0x01);
        add0x00(list, 3);
    }

    private static void add0x00(List<Integer> list, int num) {
        for (int i = 0; i < num; i++) {
            list.add(0x00);
        }
    }

    private static int crcTableLow[] = {
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
            0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01,
            0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81,
            0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
            0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
            0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01,
            0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
            0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
            0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
            0x40};
    private static int crcTableHigh[] = {
            0x00, 0xC0, 0xC1, 0x01, 0xC3, 0x03, 0x02, 0xC2, 0xC6, 0x06, 0x07, 0xC7, 0x05, 0xC5, 0xC4,
            0x04, 0xCC, 0x0C, 0x0D, 0xCD, 0x0F, 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB, 0x0B, 0xC9, 0x09,
            0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB, 0xDA, 0x1A, 0x1E, 0xDE, 0xDF, 0x1F, 0xDD,
            0x1D, 0x1C, 0xDC, 0x14, 0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13, 0xD3,
            0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3, 0xF2, 0x32, 0x36, 0xF6, 0xF7,
            0x37, 0xF5, 0x35, 0x34, 0xF4, 0x3C, 0xFC, 0xFD, 0x3D, 0xFF, 0x3F, 0x3E, 0xFE, 0xFA, 0x3A,
            0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38, 0x28, 0xE8, 0xE9, 0x29, 0xEB, 0x2B, 0x2A, 0xEA, 0xEE,
            0x2E, 0x2F, 0xEF, 0x2D, 0xED, 0xEC, 0x2C, 0xE4, 0x24, 0x25, 0xE5, 0x27, 0xE7, 0xE6, 0x26,
            0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0, 0xA0, 0x60, 0x61, 0xA1, 0x63, 0xA3, 0xA2,
            0x62, 0x66, 0xA6, 0xA7, 0x67, 0xA5, 0x65, 0x64, 0xA4, 0x6C, 0xAC, 0xAD, 0x6D, 0xAF, 0x6F,
            0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8, 0x68, 0x78, 0xB8, 0xB9, 0x79, 0xBB,
            0x7B, 0x7A, 0xBA, 0xBE, 0x7E, 0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4, 0x74, 0x75, 0xB5,
            0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71, 0x70, 0xB0, 0x50, 0x90, 0x91,
            0x51, 0x93, 0x53, 0x52, 0x92, 0x96, 0x56, 0x57, 0x97, 0x55, 0x95, 0x94, 0x54, 0x9C, 0x5C,
            0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E, 0x5A, 0x9A, 0x9B, 0x5B, 0x99, 0x59, 0x58, 0x98, 0x88,
            0x48, 0x49, 0x89, 0x4B, 0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D, 0x4D, 0x4C, 0x8C,
            0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82, 0x42, 0x43, 0x83, 0x41, 0x81, 0x80,
            0x40};

    private static FramePo transToFrame(List<Integer> list) {
        int[] crc = crcCheck(list, list.size());
        list.set(10, crc[0]);
        list.set(11, crc[1]);
        byte[] frame = new byte[list.size()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            getFrame(frame, sb, i, list.get(i));

        }
        return new FramePo(sb.toString().toUpperCase(), frame);
    }
}
