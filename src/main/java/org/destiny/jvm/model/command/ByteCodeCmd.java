package org.destiny.jvm.model.command;

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
public abstract class ByteCodeCmd {

    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    abstract void execute(StackFrame stackFrame, ExecutionResult result);

    abstract int getLength();
}
