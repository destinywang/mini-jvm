package org.destiny.jvm.loader;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

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
public class ClassFileLoaderTest {


    private ClassFileLoader classFileLoader;

    @Before
    public void before() {
        classFileLoader = new ClassFileLoader();
    }

    @Test
    public void readBinaryCode() throws Exception {
        classFileLoader.addClassPath("/Users/destiny");
//        System.out.println(classFileLoader.getClassPaths());
        byte[] bytes = classFileLoader.readBinaryCode("Employee");
        System.out.println(bytes.length);
//        parseToString(new byte[]{bytes[0], bytes[1], bytes[2], bytes[3]});
    }

    @Test
    public void addClassPath() throws Exception {
        classFileLoader.addClassPath("/Users/destiny");
        classFileLoader.addClassPath("/Users/guest");
//        int[] ints = new int[]{1, 2};
        System.out.println(classFileLoader.getClassPaths());
    }

    @Test
    public void judgeMagic() {

    }

    private String parseToString(byte[] codes) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : codes) {
            System.out.println(b);
        }
        return null;
    }


}