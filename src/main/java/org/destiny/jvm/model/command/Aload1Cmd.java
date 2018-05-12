package org.destiny.jvm.model.command;

import org.destiny.jvm.model.constant.ConstantPool;
import org.destiny.jvm.model.runtime.ExecutionResult;
import org.destiny.jvm.model.runtime.StackFrame;

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
public class Aload1Cmd extends NoOperandCmd {

    private String opCode;

    public Aload1Cmd(ConstantPool constantPool, String opCode) {
        super(constantPool);
        this.opCode = opCode;
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {

    }

    public String getOpCode() {
        return opCode;
    }

    @Override
    public String toString() {
        return "Aload1Cmd{" +
                "opCode='" + opCode + '\'' +
                '}';
    }
}
