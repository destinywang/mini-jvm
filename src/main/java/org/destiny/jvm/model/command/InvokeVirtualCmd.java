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
public class InvokeVirtualCmd extends TwoOperandCmd {

    private String opCode;
    private int operand1;
    private int operand2;

    public InvokeVirtualCmd(ConstantPool constantPool, String opCode, int operand1, int operand2) {
        super(constantPool);
        this.opCode = opCode;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {

    }

    public String getOpCode() {
        return opCode;
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    @Override
    public String toString() {
        return "InvokeVirtualCmd{" +
                "opCode='" + opCode + '\'' +
                ", operand1=" + operand1 +
                ", operand2=" + operand2 +
                '}';
    }
}
