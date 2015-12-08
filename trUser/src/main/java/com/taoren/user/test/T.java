package com.taoren.user.test;

/**
 * Created by wangshuisheng on 2015/6/29.
 */
public class T {
    public static void main(String[] args) throws Exception{
        String name = "中文";
        System.out.println(name);
        name=java.net.URLEncoder.encode(name, "UTF-8");
        System.out.println(name);
        name=java.net.URLEncoder.encode(name,"UTF-8");
        System.out.println(name);
        name=java.net.URLDecoder.decode(name, "UTF-8");
        System.out.println(name);
        name = java.net.URLDecoder.decode(name, "UTF-8");
        System.out.println(name);
    }
}
