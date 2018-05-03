package org.destiny.jvm.model.command;

import org.destiny.jvm.model.constant.ConstantPool;
import org.destiny.jvm.model.runtime.ExecutionResult;
import org.destiny.jvm.model.runtime.StackFrame;

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
public class BipushCmd extends OneOperandCmd {

    private String opCode;
    private int operand;

    public BipushCmd(ConstantPool constantPool, String opCode, int operand) {
        super(constantPool);
        this.opCode = opCode;
        this.operand = operand;
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {

    }

    public String getOpCode() {
        return opCode;
    }

    public int getOperand() {
        return operand;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }
}
