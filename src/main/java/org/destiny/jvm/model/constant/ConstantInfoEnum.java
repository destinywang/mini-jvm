package org.destiny.jvm.model.constant;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p>
 *     常量池种类枚举
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Destiny, Org. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public enum ConstantInfoEnum {

    CONSTANT_CLASS_INFO(7, "CONSTANT_CLASS_INFO"),
    CONSTANT_FIELD_INFO(9, "CONSTANT_FIELD_INFO"),
    CONSTANT_METHOD_INFO(10, "CONSTANT_METHOD_INFO"),
    CONSTANT_INTERFACE_INFO(11, "CONSTANT_INTERFACE_INFO"),
    CONSTANT_NAMEANDTYPE_INFO(12, "CONSTANT_NAMEANDTYPE_INFO"),
    CONSTANT_STRING_INFO(8, "CONSTANT_STRING_INFO"),
    CONSTANT_UTF8_INFO(1, "CONSTANT_UTF8_INFO"),
    ;


    private int tag;
    private String name;

    ConstantInfoEnum(int tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public int getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public static ConstantInfoEnum valueOf(int tag) {
        ConstantInfoEnum[] values = values();
        for (ConstantInfoEnum constantInfoEnum : values) {
            if (constantInfoEnum.tag == tag) {
                return constantInfoEnum;
            }
        }
        return null;
    }
}
