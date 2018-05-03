package org.destiny.jvm.model.constant;

import org.destiny.jvm.model.constant.detail.AbstractConstantInfo;

import java.util.List;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 *     常量池
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class ConstantPool {

    private int length;
    private List<AbstractConstantInfo> constantInfoList;

    public ConstantPool() {
    }

    public ConstantPool(int length, List<AbstractConstantInfo> constantInfoList) {
        this.length = length;
        this.constantInfoList = constantInfoList;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<AbstractConstantInfo> getConstantInfoList() {
        return constantInfoList;
    }

    public void setConstantInfoList(List<AbstractConstantInfo> constantInfoList) {
        this.constantInfoList = constantInfoList;
    }

    @Override
    public String toString() {
        return "ConstantPool{" +
                "length=" + length +
                ", constantInfoList=" + constantInfoList +
                '}';
    }
}
