package org.destiny.classplader;

import org.junit.Test;

import java.lang.reflect.Constructor;


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
public class MyClassLoaderTest {

    @Test
    public void findClass() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.findClass("org.destiny.jvm.test.Employee");
        System.out.println(clazz.getClass());
//        System.out.println(clazz instanceof org.destiny.jvm.test.Employee);
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
//            Object o = constructor.newInstance("destiny", 24);
//            System.out.println(o.toString());
        }
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        Object o = constructor.newInstance("destiny", 25);
        System.out.println(o);
    }

    @Test
    public void classPath() {
        System.out.println(this.getClass().getClassLoader().getResource(""));
    }

    public static void main(String[] args) {
        System.out.println("MyClassLoaderTest.main");
    }
}