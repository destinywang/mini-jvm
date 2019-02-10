package org.destiny.jvm.model.attr;

import org.destiny.jvm.util.ByteCodeIterator;
import org.destiny.jvm.model.AbstractAttributeInfo;

import java.util.List;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class LineNumberTable extends AbstractAttributeInfo {

    private int attributeNameIndex;             // 指向常量池，一定是LineNumberTable
    private int attributeLength;                // 当前属性长度，不包括开始的6个字节
    private int lineNumberTableLength;          // 成员数量
    private List<LineNumber> lineNumberTable;

    public LineNumber generateLineNumber(ByteCodeIterator iterator) {
        LineNumber lineNumber = new LineNumber();
        lineNumber.setStartPc(iterator.readU2ToInt());
        lineNumber.setLineNumber(iterator.readU2ToInt());
        return lineNumber;
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

    public int getLineNumberTableLength() {
        return lineNumberTableLength;
    }

    public void setLineNumberTableLength(int lineNumberTableLength) {
        this.lineNumberTableLength = lineNumberTableLength;
    }

    public List<LineNumber> getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(List<LineNumber> lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    @Override
    public String toString() {
        return "LineNumberTable{" +
                "attributeNameIndex=" + attributeNameIndex +
                ", attributeLength=" + attributeLength +
                ", lineNumberTableLength=" + lineNumberTableLength +
                ", lineNumberTable=" + lineNumberTable +
                '}';
    }

    public static class LineNumber {
        private int startPc;    // code[]数组的一个索引
        private int lineNumber; // 源文件的行号

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        @Override
        public String toString() {
            return "LineNumber{" +
                    "startPc=" + startPc +
                    ", lineNumber=" + lineNumber +
                    '}';
        }
    }
}
