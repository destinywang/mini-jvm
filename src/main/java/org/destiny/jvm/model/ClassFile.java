package org.destiny.jvm.model;

import org.destiny.jvm.model.constant.ConstantPool;
import org.destiny.jvm.model.constant.detail.AbstractConstantInfo;
import org.destiny.jvm.model.constant.detail.ConstantUtf8Info;

import java.util.List;

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
public class ClassFile {

    private String magicNumber;
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private List<String> accessFlags;
    private int thisClass;
    private int superClass;
    private int interfacesCount;
    private List<Integer> interfaces;
    private int fieldsCount;
    private List<FieldInfo> fields;
    private int methodCount;
    private List<MethodInfo> methodInfos;

    public MethodInfo getMethod(String name, String descriptorName) {
        for (MethodInfo methodInfo : methodInfos) {
            AbstractConstantInfo absNameInfo = constantPool.getConstantInfoList().get(methodInfo.getNameIndex());
            if (absNameInfo instanceof ConstantUtf8Info) {
                ConstantUtf8Info nameInfo = (ConstantUtf8Info) absNameInfo;
                String methodName = new String(nameInfo.getBytes());
                AbstractConstantInfo absDescInfo = constantPool.getConstantInfoList().get(methodInfo.getDescriptorIndex());
                if (absDescInfo instanceof ConstantUtf8Info) {
                    ConstantUtf8Info descInfo = (ConstantUtf8Info) absDescInfo;
                    String descName = new String(descInfo.getBytes());
                    if (name.equals(methodName) && descriptorName.equals(descName)) {
                        return methodInfo;
                    }
                }
            }
        }
        return null;
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public List<String> getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(List<String> accessFlags) {
        this.accessFlags = accessFlags;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public List<Integer> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Integer> interfaces) {
        this.interfaces = interfaces;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }

    public List<MethodInfo> getMethodInfos() {
        return methodInfos;
    }

    public void setMethodInfos(List<MethodInfo> methodInfos) {
        this.methodInfos = methodInfos;
    }

    @Override
    public String toString() {
        return "ClassFile{" +
                "magicNumber='" + magicNumber + '\'' +
                ", minorVersion=" + minorVersion +
                ", majorVersion=" + majorVersion +
                ", constantPool=" + constantPool +
                ", accessFlags=" + accessFlags +
                ", thisClass=" + thisClass +
                ", superClass=" + superClass +
                ", interfacesCount=" + interfacesCount +
                ", interfaces=" + interfaces +
                ", fieldsCount=" + fieldsCount +
                ", fields=" + fields +
                ", methodCount=" + methodCount +
                ", methodInfos=" + methodInfos +
                '}';
    }


}
