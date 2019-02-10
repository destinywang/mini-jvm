package org.destiny.jvm.util;

import org.destiny.jvm.model.AbstractAttributeInfo;
import org.destiny.jvm.model.attr.Code;
import org.destiny.jvm.model.attr.LineNumberTable;
import org.destiny.jvm.model.attr.LocalVariableTable;
import org.destiny.jvm.model.command.ByteCodeCmd;
import org.destiny.jvm.model.command.CommandParser;
import org.destiny.jvm.model.constant.ConstantPool;
import org.destiny.jvm.model.constant.detail.ConstantUtf8Info;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p>
 * 属性工厂，用于生产和返回属性
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class AttributeFactory {

    private static final String CODE = "Code";
    private static final String LINE_NUMBER_TABLE = "LineNumberTable";
    private static final String CONSTANT_VALUE = "ConstantValue";
    private static final String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    private static final String STACK_MAP_TABLE = "StackMapTable";

    public AbstractAttributeInfo create(ByteCodeIterator iterator, ConstantPool constantPool) {
        int nameIndex = iterator.readU2ToInt();
        AbstractAttributeInfo abstractAttributeInfo = null;
        String content = new String(((ConstantUtf8Info) constantPool.getConstantInfoList().get(nameIndex)).getBytes());
        switch (content) {
            case CODE:
                Code code = new Code();
                code.setAttributeNameIndex(nameIndex);
                code.setAttributeLength(iterator.readU4ToInt());
                code.setMaxStack(iterator.readU2ToInt());
                code.setMaxLocals(iterator.readU2ToInt());
                int codeLength = iterator.readU4ToInt();
                code.setCodeLength(codeLength);
                List<Byte> byteList = new LinkedList<>();
                byte[] bytes = iterator.read(codeLength);
                String codeStr = iterator.parse2String(bytes);
                List<ByteCodeCmd> cmds = CommandParser.parse(constantPool, codeStr);
                code.setCmds(cmds);
                for (byte b : bytes) {
                    byteList.add(b);
                }
                code.setCode(byteList);
                int exceptionTableLength = iterator.readU2ToInt();
                code.setExceptionTableLength(exceptionTableLength);
                for (int i = 0; i < exceptionTableLength; ++i) {
                    // TODO exception table
                    System.err.println("There is a exception table!");
                }
                int attributesCount = iterator.readU2ToInt();
                code.setAttributeLength(attributesCount);
                List<AbstractAttributeInfo> abstractAttributeInfoList = new LinkedList<>();
                for (int i = 0; i < attributesCount; ++i) {
                    AbstractAttributeInfo innerAbstractAttributeInfo = create(iterator, constantPool);
                    abstractAttributeInfoList.add(innerAbstractAttributeInfo);
                }
                code.setAttributes(abstractAttributeInfoList);
                abstractAttributeInfo = code;
                break;
            case LINE_NUMBER_TABLE:
                LineNumberTable lineNumberTable = new LineNumberTable();
                lineNumberTable.setAttributeNameIndex(nameIndex);
                lineNumberTable.setAttributeLength(iterator.readU4ToInt());
                int lineNumberTableLength = iterator.readU2ToInt();
                lineNumberTable.setLineNumberTableLength(lineNumberTableLength);
                List<LineNumberTable.LineNumber> lineNumberList = new LinkedList<>();
                for (int i = 0; i < lineNumberTableLength; ++ i) {
                    lineNumberList.add(lineNumberTable.generateLineNumber(iterator));
                }
                lineNumberTable.setLineNumberTable(lineNumberList);
                abstractAttributeInfo = lineNumberTable;
                break;

            case LOCAL_VARIABLE_TABLE:
                LocalVariableTable localVariableTable = new LocalVariableTable();
                localVariableTable.setAttributeNameIndex(nameIndex);
                int attributeLength = iterator.readU4ToInt();
                localVariableTable.setAttributeNameIndex(attributeLength);
                List<LocalVariableTable.LocalVariable> localVariableList = new LinkedList<>();
                for (int i = 0; i < attributeLength; ++ i) {
                    localVariableList.add(localVariableTable.generateLocalVariable(iterator));
                }
                localVariableTable.setLocalVariableTable(localVariableList);
                abstractAttributeInfo = localVariableTable;
                break;

            case STACK_MAP_TABLE:
                // TODO
                System.err.println("STACK_MAP_TABLE");
                break;

            case CONSTANT_VALUE:
                // TODO
                System.err.println("CONSTANT_VALUE");
                break;

                default:
                    throw new IllegalArgumentException(content);
        }
        return abstractAttributeInfo;
    }

}
