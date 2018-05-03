package org.destiny.jvm;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 * 字节数组迭代器，自身维护一个字节数组的下标
 * 用于按照指定要求遍历字节数组，并返回不同格式数据
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ByteCodeIterator {

    private final byte[] codes;

    private int index;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
        index = 0;
    }

    public byte[] read(int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; ++ i) {
            bytes[i] = codes[index++];
        }
        return bytes;
    }

    public String readU4ToString() {
        return parse2String(new byte[]{codes[index++], codes[index++], codes[index++], codes[index++]});
    }

    public int readU1ToInt() {
        return Byte.toUnsignedInt(codes[index++]);
    }

    public int readU2ToInt() {
        return parse2Int(new byte[]{codes[index++], codes[index++]});
    }

    public int readU4ToInt() {
        return parse2Int(new byte[]{codes[index++], codes[index++], codes[index++], codes[index++]});
    }

    /**
     * 将字节数组转换成字符串
     * @param codes 字节流
     * @return
     */
    public String parse2String(byte[] codes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < codes.length; ++ i) {
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            stringBuilder.append(strHex);
        }
        return stringBuilder.toString();
    }


    private int parse2Int(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; ++ i) {
            result = result | (bytes[bytes.length - 1 - i] & 0xFF) << (i << 3);
        }
        return result;
    }
}
