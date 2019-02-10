package org.destiny.jvm;

import org.destiny.jvm.loader.ClassFileLoader;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class MiniJVM {

    public void run(String[] classPaths, String className) {
        ClassFileLoader loader = new ClassFileLoader();

        // 添加 classPath
        for (String classPath : classPaths) {
            loader.addClassPath(classPath);
        }

        MethodArea methodArea = MethodArea.getInstance();
        methodArea.
    }

}
