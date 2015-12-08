package com.taoren.user.util.http;

import com.taoren.common.util.MD5Utils;

/**
 * Created by wangshuisheng on 2015/5/27.
 *
 * 此类主要是用于一些业务上的加密解密用
 */
public class SecretUtils {
    public static String taoRenPassword(String password){

        return MD5Utils.string2MD5(password).substring(7, 23);
    }

    public static String easemobPassword(String password){

        return MD5Utils.string2MD5("tr_" + password).substring(7, 23);
    }

    public static void main(String[] args) {
        String str = "123456";
        System.out.println(taoRenPassword(str));
    }
}
