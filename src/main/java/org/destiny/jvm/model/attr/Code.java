package org.destiny.jvm.model.attr;

import org.destiny.jvm.model.AbstractAttributeInfo;
import org.destiny.jvm.model.command.ByteCodeCmd;
import org.destiny.jvm.model.command.CommandParser;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 *     变长属性，位于method_info中
 *
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class Code extends AbstractAttributeInfo {

    private int attributeNameIndex;                     // 指向常量池，应该是UTF8Info，值为Code
    private int attributeLength;                        // 属性长度，不包括开始的6个字节
    private int maxStack;                               // 操作数栈的最大深度
    private int maxLocals;                              // 最大局部变量表个数
    private int codeLength;                             // 该方法的字节码长度
    private List<Byte> code;                            // 真正的字节码
    private List<ByteCodeCmd> cmds;                     // 字节码指令集
    private int exceptionTableLength;                   // 异常表的长度
    private List<ExceptionInfo> exceptionTable;         // 异常列表
    private int attributesCount;                        // 嵌套属性的数量
    private List<AbstractAttributeInfo> attributes;     // 嵌套属性的列表

    /**
     * 字节码指令转化器
     */
    private CommandParser commandParser;

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

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public List<Byte> getCode() {
        return code;
    }

    public void setCode(List<Byte> code) {
        this.code = code;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public void setExceptionTableLength(int exceptionTableLength) {
        this.exceptionTableLength = exceptionTableLength;
    }

    public List<ExceptionInfo> getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(List<ExceptionInfo> exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public List<AbstractAttributeInfo> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AbstractAttributeInfo> attributes) {
        this.attributes = attributes;
    }

    public List<ByteCodeCmd> getCmds() {
        return cmds;
    }

    public void setCmds(List<ByteCodeCmd> cmds) {
        this.cmds = cmds;
    }

    public CommandParser getCommandParser() {
        return commandParser;
    }

    public void setCommandParser(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    @Override
    public String toString() {
        return "Code{" +
                "attributeNameIndex=" + attributeNameIndex +
                ", attributeLength=" + attributeLength +
                ", maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                ", codeLength=" + codeLength +
                ", code=" + code +
                ", cmds=" + cmds +
                ", exceptionTableLength=" + exceptionTableLength +
                ", exceptionTable=" + exceptionTable +
                ", attributesCount=" + attributesCount +
                ", attributes=" + attributes +
                ", commandParser=" + commandParser +
                '}';
    }

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
    public static class ExceptionInfo {
        private int startPc;        // 异常处理器在code[]中生效的起始位置
        private int endPc;          // 异常处理器在code[]中生效的结束位置
        private int handlerPc;      // 表示一个异常处理器的起点
        private int catchType;      // 对常量池的索引，成员必须是CONSTANT_Class_info，表示当前异常处理器需要捕捉的异常类型

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public void setEndPc(int endPc) {
            this.endPc = endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public void setHandlerPc(int handlerPc) {
            this.handlerPc = handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }

        public void setCatchType(int catchType) {
            this.catchType = catchType;
        }

        @Override
        public String toString() {
            return "ExceptionInfo{" +
                    "startPc=" + startPc +
                    ", endPc=" + endPc +
                    ", handlerPc=" + handlerPc +
                    ", catchType=" + catchType +
                    '}';
        }
    }
}
