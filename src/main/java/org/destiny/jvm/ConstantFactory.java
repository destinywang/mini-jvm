package org.destiny.jvm;

import org.destiny.jvm.model.constant.ConstantInfoEnum;
import org.destiny.jvm.model.constant.detail.*;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 * 字节码文件的常量工厂，用于根据tag的不同返回不同常量
 * 目前暂时只实现 7，9，10，8，12，1
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantFactory {

    public AbstractConstantInfo create(ByteCodeIterator iterator) {
        int tag = iterator.readU1ToInt();
//        System.out.println("#" + tag + ": " + ConstantInfoEnum.valueOf(tag));

        AbstractConstantInfo constantInfo = null;
        switch (tag) {
            case 1:
                int length = iterator.readU2ToInt();
                constantInfo = new ConstantUtf8Info(length, iterator.read(length));
                break;
            case 7:
                constantInfo = new ConstantClassInfo(iterator.readU2ToInt());
                break;
            case 8:
                constantInfo = new ConstantStringInfo(iterator.readU2ToInt());
                break;
            case 9:
                constantInfo = new ConstantFieldRefInfo(iterator.readU2ToInt(), iterator.readU2ToInt());
                break;
            case 10:
                constantInfo = new ConstantMethodRefInfo(iterator.readU2ToInt(), iterator.readU2ToInt());
                break;
            case 12:
                constantInfo = new ConstantNameAndTypeInfo(iterator.readU2ToInt(), iterator.readU2ToInt());
                break;
            default:
                throw new IllegalArgumentException("所传参数无法对应常量池内容: " + tag);
        }
        return constantInfo;
    }

}
