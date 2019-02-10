package org.destiny.jvm;

import org.destiny.jvm.loader.ClassFileAnalyser;
import org.destiny.jvm.loader.ClassFileLoader;
import org.destiny.jvm.model.ClassFile;
import org.destiny.jvm.model.MethodInfo;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 * 方法区
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class MethodArea {

    private Map<String, ClassFile> map = new HashMap<>();

    private ClassFileLoader classFileLoader = new ClassFileLoader();

    private ClassFileAnalyser classFileAnalyser = new ClassFileAnalyser();

    private static MethodArea METHOD_AREA = new MethodArea();

    private MethodArea() {
    }

    public static MethodArea getInstance() {
        return METHOD_AREA;
    }

    public MethodInfo getMainMethod(String className) throws FileNotFoundException, IllegalAccessException {
        ClassFile classFile = findClassFile(className);
        return classFile.getMainMethod();
    }

    /**
     * 返回类的加载对象, 如果之前没有被加载, 则加载后再返回
     *
     * @param className
     * @return
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     */
    public ClassFile findClassFile(String className) throws FileNotFoundException, IllegalAccessException {
        // 如果已经被加载过, 直接返回
        if (map.get(className) != null) {
            return map.get(className);
        }

        ClassFile classFile = classFileAnalyser.analysis(classFileLoader.readBinaryCode(className));
        map.put(className, classFile);
        return classFile;
    }

}
