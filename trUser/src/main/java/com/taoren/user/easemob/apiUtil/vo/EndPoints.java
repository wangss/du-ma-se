package com.taoren.user.easemob.apiUtil.vo;


import com.taoren.user.easemob.apiUtil.comm.Constants;
import com.taoren.user.easemob.apiUtil.utils.HTTPClient;

import java.net.URL;

/**
 * HTTPClient EndPoints
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface EndPoints {

	static final URL TOKEN_APP_URL = HTTPClient.getURL(Constants.APP_KEY.replace("#", "/") + "/token");

	static final URL USERS_URL = HTTPClient.getURL(Constants.APP_KEY.replace("#", "/") + "/users");

	static final URL MESSAGES_URL = HTTPClient.getURL(Constants.APP_KEY.replace("#", "/") + "/messages");

	static final URL CHATGROUPS_URL = HTTPClient.getURL(Constants.APP_KEY.replace("#", "/") + "/chatgroups");

	static final URL CHATFILES_URL = HTTPClient.getURL(Constants.APP_KEY.replace("#", "/") + "/chatfiles");

}
