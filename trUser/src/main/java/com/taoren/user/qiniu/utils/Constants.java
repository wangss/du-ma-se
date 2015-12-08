package com.taoren.user.qiniu.utils;


/**
 * ApnsConstants
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface Constants {


	// APP_KEY
	public static String ACCESS_KEY = PropertiesUtils.getProperties().getProperty("ACCESS_KEY");
	// APP_CLIENT_ID
	public static String SECRET_KEY = PropertiesUtils.getProperties().getProperty("SECRET_KEY");

	public static String HEAD_SPACE = PropertiesUtils.getProperties().getProperty("HEAD_SPACE");
	// APP_CLIENT_ID
	public static String LABEL_SPACE = PropertiesUtils.getProperties().getProperty("LABEL_SPACE");

	public static String ASKING_SPACE = PropertiesUtils.getProperties().getProperty("ASKING_SPACE");

}
