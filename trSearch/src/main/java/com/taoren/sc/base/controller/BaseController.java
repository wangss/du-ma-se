package com.taoren.sc.base.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangshuisheng on 2015/6/15.
 */
public class BaseController {
    public void responseInfo(HttpServletResponse response, String info) throws IOException{
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cach");
        response.getWriter().print(info);
    }

}
