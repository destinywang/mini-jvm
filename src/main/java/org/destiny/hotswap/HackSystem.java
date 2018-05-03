package org.destiny.hotswap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p>
 *     为JavaClass劫持java.lang.System提供支持
 * </p>
 * ------------------------------------------------------------------
 * Corpright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2017/8/22 16:38
 */
public class HackSystem {

    public static final InputStream in = System.in;

    private static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public final static PrintStream out = new PrintStream(byteArrayOutputStream);

    public static final PrintStream err = out;

    public static String getBufferString() {
        return byteArrayOutputStream.toString();
    }

}
