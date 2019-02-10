package org.destiny.jvm.model.runtime;

import org.destiny.jvm.model.MethodInfo;

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
public class ExecutionResult {
    private boolean pauseAndRunNewFrame;


    public boolean isPauseAndRunNewFrame() {
        return pauseAndRunNewFrame;
    }

    public MethodInfo getNextMethod() {
        return null;
    }
}
