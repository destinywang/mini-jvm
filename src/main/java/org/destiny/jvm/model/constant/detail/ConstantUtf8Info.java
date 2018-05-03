package org.destiny.jvm.model.constant.detail;

import java.util.Arrays;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 *     用于表示字符常量的值
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantUtf8Info extends AbstractConstantInfo {

    private int tag = 1;
    private int length;
    private byte[] bytes;

    public ConstantUtf8Info() {
    }

    public ConstantUtf8Info(int length, byte[] bytes) {
        this.length = length;
        this.bytes = bytes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "ConstantUtf8Info{" +
                "tag=" + tag +
                ", length=" + length +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
