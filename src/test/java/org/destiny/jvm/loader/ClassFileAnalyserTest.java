package org.destiny.jvm.loader;

import com.alibaba.fastjson.JSON;
import org.destiny.jvm.model.ClassFile;
import org.destiny.jvm.model.MethodInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
public class ClassFileAnalyserTest {

    private ClassFileLoader classFileLoader;

    @Before
    public void before() {
        classFileLoader = new ClassFileLoader();
    }

    @Test
    public void readBinaryCode() throws Exception {

    }

    @Test
    public void analysis() throws Exception {
        classFileLoader.addClassPath("/Users/destiny/IdeaProjects/coderising/coderising-02-jvm/src/main/java");
        byte[] bytes = classFileLoader.readBinaryCode("org.destiny.jvm.test.Employee");
        ClassFileAnalyser analyser = new ClassFileAnalyser();
        ClassFile classFile = analyser.analysis(bytes);
        System.out.println(JSON.toJSONString(classFile));

        MethodInfo method = classFile.getMethod("main", "([Ljava/lang/String;)V");
        System.err.println(method);
    }

}