package org.destiny.jvm;

import org.destiny.jvm.model.MethodInfo;
import org.destiny.jvm.model.runtime.ExecutionResult;
import org.destiny.jvm.model.runtime.StackFrame;

import java.util.Stack;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2018/7/1 14:56
 */
public class ExecutorEngine {

    private Stack<StackFrame> stack = new Stack<>();

    public void execute(MethodInfo mainMethod) {
        StackFrame mainFrame = StackFrame.create(mainMethod);
        stack.push(mainFrame);

        while (!stack.empty()) {
            StackFrame stackFrame = stack.peek();
            ExecutionResult result = stackFrame.execute();

            if (result.isPauseAndRunNewFrame()) {
                MethodInfo nextFrame = result.getNextMethod();
            }
        }
    }
}
