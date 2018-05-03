package org.destiny.jvm.model.attr;

import org.destiny.jvm.ByteCodeIterator;
import org.destiny.jvm.model.AbstractAttributeInfo;

import java.util.List;

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
public class LocalVariableTable extends AbstractAttributeInfo {

    private int attributeNameIndex;             // 指向常量池，LocalVariableTable
    private int attributeLength;                // 当前属性长度，不包括开始的6个字节
    private int localVariableTableLength;       // 给出了下面localVariableTable[]数组成员的数量
    private List<LocalVariable> localVariableTable;

    public LocalVariable generateLocalVariable(ByteCodeIterator iterator) {
        LocalVariable localVariable = new LocalVariable();
        localVariable.setStartPc(iterator.readU2ToInt());
        localVariable.setLength(iterator.readU2ToInt());
        localVariable.setNameIndex(iterator.readU2ToInt());
        localVariable.setDescriptorIndex(iterator.readU2ToInt());
        localVariable.setIndex(iterator.readU1ToInt());
        return localVariable;
    }

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

    public int getLocalVariableTableLength() {
        return localVariableTableLength;
    }

    public void setLocalVariableTableLength(int localVariableTableLength) {
        this.localVariableTableLength = localVariableTableLength;
    }

    public List<LocalVariable> getLocalVariableTable() {
        return localVariableTable;
    }

    public void setLocalVariableTable(List<LocalVariable> localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    @Override
    public String toString() {
        return "LocalVariableTable{" +
                "attributeNameIndex=" + attributeNameIndex +
                ", attributeLength=" + attributeLength +
                ", localVariableTableLength=" + localVariableTableLength +
                ", localVariableTable=" + localVariableTable +
                '}';
    }

    public static class LocalVariable {
        private int startPc;                // 局部变量表的索引都在范围[startPc,
        private int length;                 // startPc + length)中
        private int nameIndex;              // 变量名索引（在常量池中）
        private int descriptorIndex;        // 变量描述索引（在常量池中）
        private int index;                  // 此局部变量在当前栈帧的局部变量表中的索引

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "LocalVariable{" +
                    "startPc=" + startPc +
                    ", length=" + length +
                    ", nameIndex=" + nameIndex +
                    ", descriptorIndex=" + descriptorIndex +
                    ", index=" + index +
                    '}';
        }
    }
}
