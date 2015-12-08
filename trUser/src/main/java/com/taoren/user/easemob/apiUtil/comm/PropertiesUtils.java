package com.taoren.user.easemob.apiUtil.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ApnsPropertiesUtils
 * 
 * @author Lynch 2014-09-15
 *
 */
public class PropertiesUtils {

	public static Properties getEasemobProperties() {

		Properties p = new Properties();

		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					"easemob.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}


}
