package com.taoren.user.util.http;


import com.taoren.common.constant.Constants;
import com.taoren.common.model.FileItem;
import com.taoren.common.util.TaorenUtils;
import com.taoren.common.util.WebUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * http client
 */
public class DefaultHttpClient {

	private String format = Constants.FORMAT_JSON;
	private String signMethod = Constants.SIGN_METHOD_HMAC;
	
	private int connectTimeout = 3000;//3秒
	private int readTimeout = 15000;//15秒



	public void doPost(String url,
					   Map<String, String> queryParams,
					   Map<String, String> appParams,
					   Map<String, FileItem> fileParams,
					   String charset,
					   int connectTimeout,
					   int readTimeout,
					   Map<String, String> headerMap) throws IOException{


		StringBuffer reqUrl = new StringBuffer(url);

		try {
			String query = WebUtils.buildQuery(queryParams, Constants.CHARSET_UTF8);

			if(reqUrl.indexOf("?") != -1){
				reqUrl.append("&");
			} else {
				reqUrl.append("?");
			}
			reqUrl.append(query);

		} catch (IOException e) {
			e.printStackTrace();
		}


		String rsp = null;
		try {

			// 是否需要上传文件

			boolean ifUploadFile = false;
			if (ifUploadFile) {
				fileParams = TaorenUtils.cleanupMap(fileParams);

				rsp = this.doPost(reqUrl.toString(), appParams, fileParams, Constants.CHARSET_UTF8, connectTimeout, readTimeout, headerMap);

			} else {
				rsp = this.doPost(reqUrl.toString(), appParams, Constants.CHARSET_UTF8, connectTimeout, readTimeout, headerMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


    public static void main(String[] args) throws Exception{

    }

	protected String doPost(String url, 
			Map<String, String> params, 
			String charset, 
			int connectTimeout, 
			int readTimeout,
			Map<String, String> headerMap) throws Exception {
		return WebUtils.doPost(url, params, charset, connectTimeout, readTimeout, headerMap);
	}
	
	protected String doPost(String url, 
			Map<String, String> params, 
			Map<String, FileItem> fileParams,
			String charset,
			int connectTimeout, 
			int readTimeout, 
			Map<String, String> headerMap) throws IOException {
		return WebUtils.doPost(url, params, fileParams, charset, connectTimeout, readTimeout, headerMap);
	}
}
