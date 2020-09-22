/**
 * COPYRIGHT: Copyright(c) 2013 上海久誉软件系统有限公司 All Rights Reserved.
 */
package com.thinkingcao.store.common.exception;

import java.io.IOException;
import java.io.InputStream;

/**
 * SHA-1不可逆加密工具类
 *
 * @author cirui.xu
 * @version <b>1.0.0</b>
 * @date 2019年4月25日 下午4:59:05
 */
public class Sha1Utils {

    private static final String SHA1 = "SHA-1";

    /**
     * 生成随机的Byte[]作为salt密钥.
     *
     * @param numBytes byte数组的大小
     * @return
     * @author cirui.xu
     * @date 2019年4月25日 下午4:59:12
     */
    public static byte[] genSalt(int numBytes) {
        return DigestUtils.genSalt(numBytes);
    }

    /**
     * 对输入字符串进行sha1散列.
     *
     * @param input
     * @return
     * @author cirui.xu
     * @date 2019年4月25日 下午4:59:23
     */
    public static byte[] sha1(byte[] input) {
        return DigestUtils.digest(input, SHA1, null, 1);
    }

    /**
     * 对输入字符串进行sha1散列.
     *
     * @param input
     * @param salt
     * @return
     * @author cirui.xu
     * @date 2019年4月25日 下午4:59:35
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        return DigestUtils.digest(input, SHA1, salt, 1);
    }

    /**
     * 对输入字符串进行sha1散列.
     *
     * @param input
     * @param salt
     * @param iterations
     * @return
     * @author cirui.xu
     * @date 2019年4月25日 下午4:59:43
     */
    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return DigestUtils.digest(input, SHA1, salt, iterations);
    }

    /**
     * 对文件进行sha1散列.
     *
     * @param input
     * @return
     * @throws IOException
     * @author cirui.xu
     * @date 2019年4月25日 下午4:59:50
     */
    public static byte[] sha1(InputStream input) throws IOException {
        return DigestUtils.digest(input, SHA1);
    }
}
