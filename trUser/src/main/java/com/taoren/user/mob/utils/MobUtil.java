package com.taoren.user.mob.utils;

import com.taoren.common.util.TaorenUtils;

/**
 * 发送短信
 * @author Administrator
 *
 */
public class MobUtil {
	private static final String address = "https://api.sms.mob.com/sms/verify";
	private static final String appkey = "92da5bd6d58a";

	/**
	 * ����˷�����֤������֤�ƶ���(�ֻ�)���͵Ķ���
	 * @return
	 * @throws Exception
	 */
	public static String go(String phone, String zone, String code) throws Exception{

		MobClient client = null;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			String result = client.post();
			return result;
		} finally {
			client.release();
		}
	}

	public static boolean verifyCode(String phone, int zone, String code){
		try {
			String json = go(phone,zone+"", code);
			MobReturn mobReturn = TaorenUtils.j2o(json, MobReturn.class);

			if(200 == mobReturn.getStatus()){
				return true;
			}else {
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

}
