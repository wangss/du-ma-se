package com.taoren.user.account.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ApnsPropertiesUtils
 * 
 * @author Lynch 2014-09-15
 *
 */
public class ApnsPropertiesUtils {


	public static Properties getProperties() {

		Properties p = new Properties();

		try {
			InputStream inputStream = ApnsPropertiesUtils.class.getClassLoader().getResourceAsStream(
					"apns.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

}
