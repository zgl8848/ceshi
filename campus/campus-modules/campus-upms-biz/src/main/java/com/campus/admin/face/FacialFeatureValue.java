package com.campus.admin.face;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: hulipeng
 * @Date: 2018/10/12 14:56
 * @Description: 人脸特征值计算
 */
@Component
public class FacialFeatureValue {
	private JsonParser parser = new JsonParser();

	@Value("${FACE_URL}")
	private String FACE_URL;
	
	/**
	 * 人脸归一
	 * @param photoCode
	 * @return
	 */
	public String detectFace(String photoCode) {
		StringBuffer result = new StringBuffer();
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		try {
			connection = getConn(FaceConstants.FACE_TO_A);
			OutputStream out = connection.getOutputStream();
			out.write(photoCode.getBytes());
			out.flush();
			out.close();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), FaceConstants.CODED_FORMAT));
			String lines = null;
			while ((lines = reader.readLine()) != null) {
				result.append(lines);
			}
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}


	/**
	 * 特征值计算
	 *
	 * @param photoCode
	 * @return
	 */
	public String calculateFeatur(String photoCode) {
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		StringBuffer result = new StringBuffer();
		try {
			connection = getConn(FaceConstants.EIGENVALUE_CALCULATION);
			OutputStream out = connection.getOutputStream();
			out.write(photoCode.getBytes());
			out.flush();
			out.close();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), FaceConstants.CODED_FORMAT));
			String lines;
			while ((lines = reader.readLine()) != null) {
				result.append(lines);
			}
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}


	/**
	 * 识别
	 *
	 * @param fea1
	 * @param fea2
	 * @return
	 */
	public String getScore(String fea1, String fea2) {
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		String result = null;
		try {
			JSONObject obj = new JSONObject();
			obj.put("fea1", fea1);
			obj.put("fea2", fea2);
			connection = getConn(FaceConstants.FACE_COMPARISON);
			OutputStream out = connection.getOutputStream();
			out.write(obj.toString().getBytes());
			out.flush();
			out.close();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), FaceConstants.CODED_FORMAT));
			String lines;
			while ((lines = reader.readLine()) != null) {
				result = lines;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 人脸特征值计算
	 *
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	public String getFacialFeatureValue(JSONArray array) throws JSONException {
		String feal = "";
		if (array != null && array.length() > 0) {
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String tmp = obj.getString(FaceConstants.BASE64_NAME);
				//调用计算人脸特征值
				String featur = this.calculateFeatur(tmp);
				JsonObject asJsonObject = parser.parse(featur).getAsJsonObject();
				feal = asJsonObject.get(FaceConstants.EIGENVALUE_KEY).getAsString();
			}
		} else {
			return null;
		}
		return feal;
	}

	/**
	 * 创建HTTP连接
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpURLConnection getConn(String url) throws Exception {
		URL realUrl;
		HttpURLConnection connection = null;
		realUrl = new URL(url);
		connection = (HttpURLConnection) realUrl.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("contentType", "application/json");
		connection.connect();
		return connection;
	}
}
