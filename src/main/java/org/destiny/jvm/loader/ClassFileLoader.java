package org.destiny.jvm.loader;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ClassFileLoader {

    /**
     * 用于存储loader的所有classpath
     */
    private List<String> classPaths = new LinkedList<>();

    /**
     * 添加一个classpath
     * @param path
     */
    public void addClassPath(String path) {
        classPaths.add(path);
    }

    /**
     * 魔数
     */
    private static final String MAGIC_NUMBER = "cafebabe";

    /**
     * 返回classPath
     * @return
     */
    public String getClassPaths() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String path : classPaths) {
            stringBuilder.append(path).append(";");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    /**
     * 将指定classpath和class文件的相对路径进行拼接，返回文件的字节流
     * @param className class文件的相对路径
     * @return
     * @throws IllegalAccessException
     * @throws FileNotFoundException
     */
    public byte[] readBinaryCode(String className) throws IllegalAccessException, FileNotFoundException {
        if (classPaths.isEmpty()) {
            throw new IllegalAccessException("classPaths未设置");
        }
        className = className.replace('.', File.separatorChar) + ".class";
        for (String path : classPaths) {
            String classFileName = path + File.separatorChar + className;
            byte[] codes = loadClassFile(classFileName);
            if (codes != null) {
                return codes;
            }
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

    private byte[] loadClassFileOld(String classFileName) {
        File file = new File(classFileName);
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fileInputStream.read(buffer)) > 0) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 验证该字节流的前8个字节是否是class文件的魔数
     * @param codes
     * @return
     */
    private boolean checkMagicNumber(byte[] codes) {
        return MAGIC_NUMBER.equals(byteToHexString(codes));
    }

    /**
     * 将字节数组转换成字符串
     * @param codes 字节流
     * @return
     */
    private String byteToHexString(byte[] codes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < codes.length; ++ i) {
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            stringBuilder.append(strHex);
        }
        return stringBuilder.toString();
    }
}
