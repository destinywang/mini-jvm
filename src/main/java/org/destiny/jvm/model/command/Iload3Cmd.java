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
public class Iload3Cmd extends NoOperandCmd {

    private String opCode;

    public Iload3Cmd(ConstantPool constantPool, String opCode) {
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
        return "Iload3Cmd{" +
                "opCode='" + opCode + '\'' +
                '}';
    }
}
