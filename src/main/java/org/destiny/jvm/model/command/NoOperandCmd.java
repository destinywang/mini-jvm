package org.destiny.jvm.model.command;

import org.destiny.jvm.model.constant.ConstantPool;

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
public abstract class NoOperandCmd extends ByteCodeCmd {

    private ConstantPool constantPool;

    public NoOperandCmd(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getLength() {
        return 1;
    }

    @Override
    public String toString() {
        return "NoOperandCmd{" +
                "constantPool=" + constantPool +
                '}';
    }
}
