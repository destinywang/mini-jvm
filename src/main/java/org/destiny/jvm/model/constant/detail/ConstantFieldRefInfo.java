package org.destiny.jvm.model.constant.detail;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantFieldRefInfo extends AbstractConstantInfo {

    private int tag = 9;

    /**
     * 对常量池中 CONSTANTS_Class_info结构的有效索引
     */
    private int classIndex;

    /**
     * 对常量池中 CONSTANTS_NameAndType_info的有效索引
     */
    private int nameAndTypeIndex;

    public ConstantFieldRefInfo() {
    }

    public ConstantFieldRefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public String toString() {
        return "ConstantFieldRefInfo{" +
                "tag=" + tag +
                ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                '}';
    }
}
