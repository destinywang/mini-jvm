package org.destiny.jvm.model.constant.detail;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p>
 *     用于表示字段或方法，但没有指明该字段或方法所属的类或接口
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantNameAndTypeInfo extends AbstractConstantInfo {

    private int tag = 12;

    /**
     * 对常量池中 CONSTANTS_Utf8_info结构的索引
     */
    private int nameIndex;

    /**
     * 对常量池中 CONSTANTS_Utf8_info结构的索引
     */
    private int descriptorIndex;

    public ConstantNameAndTypeInfo() {
    }

    public ConstantNameAndTypeInfo(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public String toString() {
        return "ConstantNameAndTypeInfo{" +
                "tag=" + tag +
                ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                '}';
    }
}
