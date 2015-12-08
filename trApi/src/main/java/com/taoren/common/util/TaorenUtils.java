package com.taoren.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoren.common.constant.Constants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * taoren系统工具类。
 * 内置有md5、hmac加密方法
 */
public abstract class TaorenUtils {
	private static String localIp;

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}


	public static String mapToString(Object object){
		try{
			return objectMapper.writeValueAsString(object);
		}catch (Exception e){
			return null;
		}

	}

	public static  <T> T mapToObject(String source, Class<T> clazz){
		try{
			return objectMapper.readValue(source, clazz);
		}catch (Exception e){
			return null;
		}

	}


	/**
	 * 获取uuid， 没有-
	 * @return
	 */
	public static String getUUIDnoDash(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 请求签名。
	 * 
	 * @param params 请求参数
	 * @param secret 私有的东西，放在最前面
	 * @return 签名
	 * @throws IOException
	 */
	public static String sign(Map<String, String> params, String secret){
		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder(secret);
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				query.append(key).append(value);
			}
		}

		String str = query.toString();
		try{
			str = java.net.URLEncoder.encode(str, "utf-8");
			System.out.println("str = " + str);
			System.out.println("sign = " + MD5Utils.string2MD5(str));
		}catch (Exception e){

		}
		// 第三步：使用MD5加密
		return MD5Utils.string2MD5(str);

	}



	private static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			String msg=getStringFromException(gse);
			throw new IOException(msg);
		}
		return bytes;
	}

	private static String getStringFromException(Throwable e) {
		String result = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(bos);
		e.printStackTrace(ps);
		try {
			result = bos.toString(Constants.CHARSET_UTF8);
		} catch (IOException ioe) {
		}
		return result;
	}




	/**
	 * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes 文件字节流
	 * @return JPG, GIF, PNG or null
	 */
	public static String getFileSuffix(byte[] bytes) {
		if (bytes == null || bytes.length < 10) {
			return null;
		}

		if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
			return "GIF";
		} else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
			return "PNG";
		} else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
			return "JPG";
		} else if (bytes[0] == 'B' && bytes[1] == 'M') {
			return "BMP";
		} else {
			return null;
		}
	}

	/**
	 * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes 文件字节流
	 * @return 媒体类型(MEME-TYPE)
	 */
	public static String getMimeType(byte[] bytes) {
		String suffix = getFileSuffix(bytes);
		String mimeType;

		if ("JPG".equals(suffix)) {
			mimeType = "image/jpeg";
		} else if ("GIF".equals(suffix)) {
			mimeType = "image/gif";
		} else if ("PNG".equals(suffix)) {
			mimeType = "image/png";
		} else if ("BMP".equals(suffix)) {
			mimeType = "image/bmp";
		}else {
			mimeType = "application/octet-stream";
		}

		return mimeType;
	}

	/**
	 * 清除字典中值为空的项。
	 * 
	 * @param <V> 泛型
	 * @param map 待清除的字典
	 * @return 清除后的字典
	 */
	public static <V> Map<String, V> cleanupMap(Map<String, V> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, V> result = new HashMap<String, V>(map.size());
		Set<Entry<String, V>> entries = map.entrySet();

		for (Entry<String, V> entry : entries) {
			if (entry.getValue() != null) {
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return result;
	}



	/**
	 * 获取本机的网络IP
	 */
	public static String getLocalNetWorkIp() {
		if (localIp != null) {
			return localIp;
		}
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {// 遍历所有的网卡
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				if (ni.isLoopback() || ni.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
					continue;
				}
				Enumeration<InetAddress> addresss = ni.getInetAddresses();
				while (addresss.hasMoreElements()) {
					InetAddress address = addresss.nextElement();
					if (address instanceof Inet4Address) {// 这里暂时只获取ipv4地址
						ip = address;
						break;
					}
				}
				if (ip != null) {
					break;
				}
			}
			if (ip != null) {
				localIp = ip.getHostAddress();
			} else {
				localIp = "127.0.0.1";
			}
		} catch (Exception e) {
			localIp = "127.0.0.1";
		}
		return localIp;
	}


	public static final String o2j(Object o){
		return JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);
	}

	public static final String o2jPretty(Object o, boolean ifpretty){
		if(ifpretty){
			return JSON.toJSONString(o, SerializerFeature.PrettyFormat,  SerializerFeature.DisableCircularReferenceDetect);
		}
		return JSON.toJSONString(o);
	}

	public static final <T> T j2o(String str, Class<T> clazz){
		return JSON.parseObject(str, clazz);
	}

	public static final <T> List<T> j2List(String str, Class<T> clazz){
		return JSON.parseArray(str,clazz);
	}

	/**
	 * 根据生日年龄获取出生年月
	 * @param birthday
	 * @param age
	 * @return
	 */
	public static Date getBirthDay(Date birthday, int age){

		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);//date 换成已经已知的Date对象
		cal.add(Calendar.YEAR, -age);
		return cal.getTime();
	}


	/**
	 * 根据生日年龄获取出生年月
	 * @param birthday
	 * @param age
	 * @return
	 */
	public static long getBirthDayMillis(Date birthday, int age){

		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);//date 换成已经已知的Date对象
		cal.add(Calendar.YEAR, -age);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取前几分钟的date
	 * @param
	 * @return
	 */
	public static Date getBeforeDate(Date date, int minute){

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -minute);
		return cal.getTime();
	}

	public static long getBeforeDateMillis(Date date, int minute){

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, - minute);
		return cal.getTimeInMillis();
	}



	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public static String getDateSlashFormat(Date date){

		return new SimpleDateFormat(Constants.DATE_SLASH_FORMAT).format(date);
	}

    public static void main(String[] args){
//
//		String uuid = UUID.randomUUID().toString();
//		System.out.println(uuid);
//		System.out.println(uuid.length());
//		System.out.println(uuid.replace("-", ""));


//		TaorenUtils.o2j(null);
	}

}
