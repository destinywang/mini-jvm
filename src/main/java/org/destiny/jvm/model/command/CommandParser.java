package org.destiny.jvm.model.command;

import org.destiny.jvm.CommandIterator;
import org.destiny.jvm.model.ClassFile;
import org.destiny.jvm.model.constant.ConstantPool;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
public class CommandParser {

    public static final String ACONST_NULL = "01";
    public static final String NEW_OBJECT = "BB";
    public static final String LSTORE = "37";
    public static final String INVOKESPECIAL = "B7";
    public static final String INVOKEVIRTUAL = "B6";
    public static final String GETFIELD = "B4";
    public static final String PUTFIELD = "B5";
    public static final String GETSTATIC = "B2";
    public static final String LDC = "12";
    public static final String DUP = "59";
    public static final String BIPUSH = "10";
    public static final String ALOAD_0 = "2A";
    public static final String ALOAD_1 = "2B";
    public static final String ALOAD_2 = "2C";
    public static final String ILOAD = "15";
    public static final String ILOAD_1 = "1B";
    public static final String ILOAD_2 = "1C";
    public static final String ILOAD_3 = "1D";
    public static final String FLOAD_3 = "25";

    public static final String ATRTURN = "B0";
    public static final String VOIDRETURN = "B1";
    public static final String IRETURN = "AC";
    public static final String FRETURN = "AE";

    public static final String ASTORE_1 = "4C";
    public static final String IF_ICMP_GE = "A2";
    public static final String IF_ICMPLE = "A4";
    public static final String GOTO_NO_CONDITION = "A7";
    public static final String ICONST_0 = "03";
    public static final String ICONST_1 = "04";
    public static final String ISTORE_1 = "3C";
    public static final String ISTORE_2 = "3D";
    public static final String IADD = "60";
    public static final String IINC = "84";


    public static List<ByteCodeCmd> parse(ConstantPool constantPool, String codes) {
        codes = codes.toUpperCase();
        List<ByteCodeCmd> cmdList = new LinkedList<>();
        CommandIterator iterator = new CommandIterator(codes);
        while (iterator.hasNext()) {
            String opCode = iterator.next2CharAsString();

            ByteCodeCmd cmd = null;
            switch (opCode) {
                case NEW_OBJECT:
                    cmd = new NewObjectCmd(constantPool, opCode, iterator.next2CharAsInt(), iterator.next2CharAsInt());
                    break;
                case INVOKESPECIAL:
                    cmd = new InvokeSpecialCmd(constantPool, opCode, iterator.next2CharAsInt(), iterator.next2CharAsInt());
                    break;
                case INVOKEVIRTUAL:
                    cmd = new InvokeVirtualCmd(constantPool, opCode, iterator.next2CharAsInt(), iterator.next2CharAsInt());
                    break;
                case GETFIELD:
                    cmd = new GetFieldCmd(constantPool, opCode, iterator.next2CharAsInt(), iterator.next2CharAsInt());
                    break;
                case GETSTATIC:
                    cmd = new GetStaticFieldCmd(constantPool, opCode, iterator.next2CharAsInt(), iterator.next2CharAsInt());
                    break;
                case PUTFIELD:
                    cmd = new PutFieldCmd(constantPool, opCode, iterator.next2CharAsInt(), iterator.next2CharAsInt());
                    break;
                case LDC:
                    cmd = new LdcCmd(constantPool, opCode, iterator.next2CharAsInt());
                    break;
                case BIPUSH:
                    cmd = new BipushCmd(constantPool, opCode, iterator.next2CharAsInt());
                    break;
                case DUP:
                    cmd = new DupCmd(constantPool, opCode);
                    break;
                case ALOAD_0:
                    cmd = new Aload0Cmd(constantPool, opCode);
                    break;
                case ALOAD_1:
                    cmd = new Aload1Cmd(constantPool, opCode);
                    break;
                case ALOAD_2:
                    cmd = new Aload2Cmd(constantPool, opCode);
                    break;
                case ILOAD:
                    cmd = new IloadCmd(constantPool, opCode);
                    break;
                case ILOAD_1:
                    cmd = new Iload1Cmd(constantPool, opCode);
                    break;
                case ILOAD_2:
                    cmd = new Iload2Cmd(constantPool, opCode);
                    break;
                case ILOAD_3:
                    cmd = new Iload3Cmd(constantPool, opCode);
                    break;
                case FLOAD_3:
                    cmd = new Fload3Cmd(constantPool, opCode);
                    break;
                case VOIDRETURN:
                    cmd = new VoidReturnCmd(constantPool, opCode);
                    break;
                case ASTORE_1:
                    cmd = new AStore1Cmd(constantPool, opCode);
                    break;
                default:
                    throw new RuntimeException("the java instruction " + opCode + " is invalid");
            }
            cmdList.add(cmd);
            calcuateOffset(cmdList);
        }
        return cmdList;
    }

    private static void calcuateOffset(List<ByteCodeCmd> codeCmds) {
        int offset = 0;
        for (ByteCodeCmd byteCodeCmd : codeCmds) {
            byteCodeCmd.setOffset(offset);
            offset += byteCodeCmd.getLength();
        }
    }
}
