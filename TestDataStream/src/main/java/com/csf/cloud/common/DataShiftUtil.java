package com.csf.cloud.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class DataShiftUtil {
    /**
     * replaced by com.aug3.sys.util.MD5.md5(String)
     * @param str
     * @return
     * @deprecated
     */
    public static String getMD5Str(String str) {
        try {
            return getMD5Str(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return getMD5Str(str.getBytes());
        }
    }

    /**
     * replaced by com.aug3.sys.util.MD5.getMD5Str(InputStream)
     * @deprecated
     */
    public static String getMD5Str(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            return "";
        }
    }

}
