package org.destiny.jvm.model.constant.detail;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 *     用于表示类或接口
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantClassInfo extends AbstractConstantInfo {

    private int tag = 7;
    private int nameIndex;

    public ConstantClassInfo() {
    }

    public ConstantClassInfo(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return "ConstantClassInfo{" +
                "tag=" + tag +
                ", nameIndex=" + nameIndex +
                '}';
    }
}
