package com.taoren.user.easemob.apiUtil.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taoren.user.easemob.apiUtil.comm.Constants;
import com.taoren.user.easemob.apiUtil.comm.HTTPMethod;
import com.taoren.user.easemob.apiUtil.vo.EndPoints;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * REST API Demo :�û���ϵ���� REST API HttpClient4.3ʵ��
 *
 * Doc URL: http://www.easemob.com/docs/rest/userapi
 *
 * @author Lynch 2014-09-15
 *
 */
public class EasemobUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobUtil.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);


    public static void main(String[] args) {

//		String token = "YWMtEd09ihlxEeW7ZW8vhz0UOwAAAU9UGCT9r9AYOJJOGNId1H9Xcqm9tX9Aofc";
//		createNewIMUser("testname", "testpassword", token);
		ObjectNode objectNode = factory.objectNode();
		objectNode.put("grant_type", 200);
		System.out.println(objectNode.get("grant_type").asInt() == 200);
	}


	/**
	 * ��ȡ appToken, Ĭ����Ч��Ϊ7��
	 *
	 */
	public static String getAppToken() throws Exception{

		ObjectNode objectNode = factory.objectNode();
		objectNode.put("grant_type", "client_credentials");
		objectNode.put("client_id", Constants.APP_CLIENT_ID);
		objectNode.put("client_secret", Constants.APP_CLIENT_SECRET);
		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		HttpPost httpPost = new HttpPost();
		httpPost.setURI(EndPoints.TOKEN_APP_URL.toURI());

		for (NameValuePair nameValuePair : headers) {
			httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
		}
		httpPost.setEntity(new StringEntity(objectNode.toString(), "UTF-8"));

		HttpResponse tokenResponse = HTTPClient.getClient(true).execute(httpPost);
		HttpEntity entity = tokenResponse.getEntity();

		String results = EntityUtils.toString(entity, "UTF-8");


		if (tokenResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

			ObjectMapper mapper = new ObjectMapper();

			JsonFactory factory = mapper.getJsonFactory();
			JsonParser jp = factory.createJsonParser(results);
			JsonNode json = mapper.readTree(jp);

			String accessToken = json.get("access_token").asText();

			return accessToken;
		}
		return null;
	}

    /**
	 * ע��IM�û�[����]
	 *
	 * ��ָ��ApnsConstants.APPKEY����һ���µ��û�
	 *
	 * @return
	 */
	public static boolean createNewIMUser(String username, String password, String appToken) {

		ObjectNode objectNode = factory.objectNode();
		objectNode.put("username", username);
		objectNode.put("password", password);
		try {

		    objectNode = HTTPClient.sendHTTPRequest(EndPoints.USERS_URL, appToken, objectNode,
					HTTPMethod.METHOD_POST);
			if(objectNode == null || objectNode.get("statusCode").asInt() != 200){

				objectNode = HTTPClient.sendHTTPRequest(EndPoints.USERS_URL, appToken, objectNode,
						HTTPMethod.METHOD_POST);
			}
			if(objectNode.get("statusCode").asInt() == 200){
				return true;
			}else{
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * ע��IM�û�[����]
	 *
	 * ��ָ��ApnsConstants.APPKEY����һ���µ��û�
	 *
	 * @return
	 */
	public static boolean modifyIMUserPassword(String username, String newPassword, String appToken) {
		ObjectNode objectNode = factory.objectNode();
		objectNode.put("newpassword", newPassword);

		try {
			URL modifyIMUserPasswordWithAdminTokenUrl = HTTPClient.getURL(Constants.APP_KEY.replace("#", "/")
					+ "/users/" + username + "/password");
			objectNode = HTTPClient.sendHTTPRequest(modifyIMUserPasswordWithAdminTokenUrl, appToken,
					objectNode, HTTPMethod.METHOD_PUT);

			if(objectNode == null || objectNode.get("statusCode").asInt() != 200){//�ٵ�һ��

				objectNode = HTTPClient.sendHTTPRequest(modifyIMUserPasswordWithAdminTokenUrl, appToken,
						objectNode, HTTPMethod.METHOD_PUT);
			}
			if(objectNode.get("statusCode").asInt() == 200){
				return true;
			}else{
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送消息
	 *
	 * @param appToken
	 *            appToken
	 * @param targetUid
	 *            接收者ID 必须是数组,数组元素为用户ID或者群组ID
	 * @param msg
	 *            消息内容
	 *
	 * @return 请求响应
	 */
	public static boolean sendMessages(String appToken, Long targetUid,String msg) {

		ObjectNode dataNode = factory.objectNode();

		ArrayNode targetUser = factory.arrayNode();
		targetUser.add("tr_" + targetUid);


		try {
			ObjectNode objectNode = factory.objectNode();
			// 构造消息体
			dataNode.put("target_type", "users");
			dataNode.put("target", targetUser);
			ObjectNode txtmsg = factory.objectNode();
			txtmsg.put("msg", msg);
			txtmsg.put("type","txt");

			dataNode.put("msg", txtmsg);
//			dataNode.put("from", from);
//			dataNode.put("ext", ext);

			objectNode = HTTPClient.sendHTTPRequest(EndPoints.MESSAGES_URL, appToken, dataNode,
					HTTPMethod.METHOD_POST);

			if(objectNode == null || objectNode.get("statusCode").asInt() != 200){

				objectNode = HTTPClient.sendHTTPRequest(EndPoints.MESSAGES_URL, appToken, dataNode,
						HTTPMethod.METHOD_POST);
			}
			if(objectNode.get("statusCode").asInt() == 200){
				return true;
			}else{
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

}
