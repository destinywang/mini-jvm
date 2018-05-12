package org.destiny.jvm.model;

import java.util.ArrayList;
import java.util.List;

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
public class AccessFlag {

    private static final int ACC_PUBLIC = 0x0001;
    private static final int ACC_PRIVATE = 0x0002;
    private static final int ACC_FINAL = 0x0010;
    private static final int ACC_SUPER = 0x0020;
    private static final int ACC_INTERFACE = 0x0200;
    private static final int ACC_ABSTRACT = 0x0400;
    private static final int ACC_SYNTHETIC = 0x1000;
    private static final int ANNOTATION = 0x2000;
    private static final int ENUM = 0x4000;

    public static List<String> getAccessFlag(int flag) {
        List<String> flags = new ArrayList<>(8);
        if ((flag & AccessFlagEnum.PUBLIC.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.PUBLIC.getAccessName());
        }
        if ((flag & AccessFlagEnum.FINAL.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.FINAL.getAccessName());
        }
        if ((flag & AccessFlagEnum.SUPER.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.SUPER.getAccessName());
        }
        if ((flag & AccessFlagEnum.INTERFACE.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.INTERFACE.getAccessName());
        }
        if ((flag & AccessFlagEnum.ABSTRACT.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.ABSTRACT.getAccessName());
        }
        if ((flag & AccessFlagEnum.SYNTHETIC.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.SYNTHETIC.getAccessName());
        }
        if ((flag & AccessFlagEnum.ANNOTATION.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.ANNOTATION.getAccessName());
        }
        if ((flag & AccessFlagEnum.ENUM.getAccessValue()) != 0) {
            flags.add(AccessFlagEnum.ENUM.getAccessName());
        }
        return flags;
    }
}
