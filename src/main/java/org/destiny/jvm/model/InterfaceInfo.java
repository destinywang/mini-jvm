package org.destiny.jvm.model;

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
public class InterfaceInfo {

    private List<Integer> interfaces;

    public List<Integer> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Integer> interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public String toString() {
        return "InterfaceInfo{" +
                "interfaces=" + interfaces +
                '}';
    }
}
