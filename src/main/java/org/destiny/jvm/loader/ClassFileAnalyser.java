package org.destiny.jvm.loader;

import org.destiny.jvm.AttributeFactory;
import org.destiny.jvm.ByteCodeIterator;
import org.destiny.jvm.ConstantFactory;
import org.destiny.jvm.model.*;
import org.destiny.jvm.model.constant.ConstantPool;
import org.destiny.jvm.model.constant.detail.AbstractConstantInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p>
 *     类的解析器，负责根据读入的字节流解析class文件
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ClassFileAnalyser {

    private ConstantFactory constantFactory;

    private AttributeFactory attributeFactory;

    private ClassFile classFile;

    public ClassFile analysis(byte[] codes) {
        classFile = new ClassFile();
        // 字节码迭代器
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        classFile.setMagicNumber(iterator.readU4ToString());
        classFile.setMinorVersion(iterator.readU2ToInt());
        classFile.setMajorVersion(iterator.readU2ToInt());
        int length = iterator.readU2ToInt();
        ConstantPool constantPool = new ConstantPool(length, generateConstantPool(iterator, length));
        classFile.setConstantPool(constantPool);
        classFile.setAccessFlags(accessFlag(iterator));
        classFile.setThisClass(iterator.readU2ToInt());
        classFile.setSuperClass(iterator.readU2ToInt());
        int interfacesCount = iterator.readU2ToInt();
        classFile.setInterfacesCount(interfacesCount);
        List<Integer> interfaces = new LinkedList<>();
        for (int i = 0; i < interfacesCount; ++ i) {
            interfaces.add(iterator.readU2ToInt());
        }
        classFile.setInterfaces(interfaces);
        int fieldCount = iterator.readU2ToInt();
        classFile.setFieldsCount(fieldCount);
        classFile.setFields(generateFields(iterator, fieldCount));
        int methodCount = iterator.readU2ToInt();
        classFile.setMethodCount(methodCount);
        classFile.setMethodInfos(generateMethods(iterator, methodCount, constantPool));
        return classFile;
    }

    private List<AbstractConstantInfo> generateConstantPool(ByteCodeIterator iterator, int length) {
        constantFactory = new ConstantFactory();
        List<AbstractConstantInfo> constantInfoList = new ArrayList<>(length + 1);
        constantInfoList.add(new AbstractConstantInfo() {});
        for (int i = 1; i < length; ++ i) {
            constantInfoList.add(constantFactory.create(iterator));
        }
        return constantInfoList;
    }

    private List<String> accessFlag(ByteCodeIterator iterator) {
        int accessFlag = iterator.readU2ToInt();
        return AccessFlag.getAccessFlag(accessFlag);
    }

    private List<FieldInfo> generateFields(ByteCodeIterator iterator, int count) {
        List<FieldInfo> fieldInfoList = new LinkedList<>();
        for (int i = 0; i < count; ++ i) {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setAccessFlags(iterator.readU2ToInt());
            fieldInfo.setNameIndex(iterator.readU2ToInt());
            fieldInfo.setDescriptorIndex(iterator.readU2ToInt());
            int attrCount = iterator.readU2ToInt();
            for (int j = 0; j < attrCount; ++ j) {
                // TODO 字段的属性解析，目前还没有实现
            }
            fieldInfoList.add(fieldInfo);
        }
        return fieldInfoList;
    }

    private List<MethodInfo> generateMethods(ByteCodeIterator iterator, int count, ConstantPool constantPool) {
        attributeFactory = new AttributeFactory();
        List<MethodInfo> methodInfoList = new LinkedList<>();
        for (int i = 0; i < count; ++ i) {
            MethodInfo methodInfo = new MethodInfo();
            methodInfo.setAccessFlags(iterator.readU2ToInt());
            methodInfo.setNameIndex(iterator.readU2ToInt());
            methodInfo.setDescriptorIndex(iterator.readU2ToInt());
            int attrCount = iterator.readU2ToInt();
            methodInfo.setAttributesCount(attrCount);
            List<AbstractAttributeInfo> abstractAttributeInfoList = new LinkedList<>();
            for (int j = 0; j < attrCount; ++ j) {
                abstractAttributeInfoList.add(attributeFactory.create(iterator, constantPool));
            }
            methodInfo.setAttributes(abstractAttributeInfoList);
            methodInfoList.add(methodInfo);
        }
        return methodInfoList;
    }
}
