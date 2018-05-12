package org.destiny.jvm.model;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p>
 *     访问标志
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public enum AccessFlagEnum {

    PUBLIC("ACC_PUBLIC", 0x0001, "声明为public，可以从包外访问"),
    PRIVATE("ACC_PRIVATE", 0x0002, "声明为private"),
    PROTECTED("ACC_PROTECTED", 0x0004, "声明为protected"),
    STATIC("ACC_STATIC", 0x0008, "是否为static"),
    FINAL("ACC_FINAL", 0x0010, "是否为final"),
    SYNCHRONIZED("ACC_SYNCHRONIZED", 0x0020, "是否为synchronized"),
    SUPER("ACC_SUPER", 0x0020, "当用到invokespecial指令时，需要对父类方法做特殊处理"),
    INTERFACE("ACC_INTERFACE", 0x200, "该class文件定义的是接口而不是类"),
    ABSTRACT("ACC_ABSTRACT", 0x400, "声明为abstract，不能被实例化"),
    SYNTHETIC("ACC_SYNTHETIC", 0x1000, "表示该class文件并非由Java源代码所生成"),
    ANNOTATION("ACC_ANNOTATION", 0x2000, "标识注解类型"),
    ENUM("ACC_ENUM", 0x4000, "标识枚举类型")
    ;

    private String accessName;
    private int accessValue;
    private String accessDesc;

    AccessFlagEnum(String accessName, int accessValue, String accessDesc) {
        this.accessName = accessName;
        this.accessValue = accessValue;
        this.accessDesc = accessDesc;
    }

    public String getAccessName() {
        return accessName;
    }

    public int getAccessValue() {
        return accessValue;
    }

    public String getAccessDesc() {
        return accessDesc;
    }
}
