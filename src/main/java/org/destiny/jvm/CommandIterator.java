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
public class CommandIterator {

    private String codes;
    
    private int index;

    public CommandIterator(String codes) {
        this.codes = codes;
        index = 0;
    }

    public boolean hasNext() {
        return index < codes.length();
    }
    
    public String next2CharAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codes.charAt(index++));
        stringBuilder.append(codes.charAt(index++));
        return stringBuilder.toString();
    }

    public int next2CharAsInt() {
        String s = next2CharAsString();
        return Integer.valueOf(s, 16);
    }
}
