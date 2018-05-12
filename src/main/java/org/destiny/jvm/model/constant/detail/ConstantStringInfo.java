package org.destiny.jvm.model.constant.detail;

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
public class ConstantStringInfo extends AbstractConstantInfo {

    private int tag = 8;
    private int stringIndex;

    public ConstantStringInfo() {
    }

    public ConstantStringInfo(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public String toString() {
        return "ConstantStringInfo{" +
                "tag=" + tag +
                ", stringIndex=" + stringIndex +
                '}';
    }
}
