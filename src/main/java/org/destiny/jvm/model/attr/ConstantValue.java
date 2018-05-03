package org.destiny.jvm.model.attr;

import org.destiny.jvm.model.AbstractAttributeInfo;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 *     定长属性
 *     如果该字段为静态字段，即field_info结构的access_flags项设置了ACC_STATIC标志
 *     则说明这个field_info结构所表示的字段，将赋值为它的ConstantValue属性所表示的值
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantValue extends AbstractAttributeInfo {

    private int attributeNameIndex;         // 常量池的索引
    private int attributeLength;           // 固定为2
    private int constantValueIndex;         // 常量池的索引，常量池在该位置给出了给出了该属性的常量值

    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public String toString() {
        return "ConstantValue{" +
                "attributeNameIndex=" + attributeNameIndex +
                ", attributeLength=" + attributeLength +
                ", constantValueIndex=" + constantValueIndex +
                '}';
    }
}
