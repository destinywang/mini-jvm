package org.destiny.classplader;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取字节数组
        byte[] bytes = loadByteCode(name);
        if (bytes == null) {
            throw new ClassNotFoundException();
        } else {
            //
            return defineClass(name, bytes, 0, bytes.length);
        }
    }

    private byte[] loadByteCode(String name) {
        String fileName = "/Users/destiny"
                + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
        try (InputStream inputStream = new FileInputStream(fileName);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadClassFile(String classFileName) {
        File file = new File(classFileName);
        try {
            return IOUtils.toByteArray(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = Class.forName("org.destiny.classplader.MyClassLoader", true, myClassLoader);
        Object o = clazz.newInstance();
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());
    }
}
