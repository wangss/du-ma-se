package com.taoren.user.account.util;


/**
 * ApnsConstants
 * 
 * @author wangshuisheng
 *
 */
public interface ApnsConstants {




	//-- apns --------------------------------------------------------------------------------------------------------------

	public static String APNS_KEY_PATH = ApnsPropertiesUtils.getProperties().getProperty("KYEPATH");
	public static String APNS_PASSWORD = ApnsPropertiesUtils.getProperties().getProperty("PASSWORD");
}
