package com.hcdz.hk.impl;

import com.hcdz.exception.ServiceException;
import com.hcdz.hk.config.SchoolAndEventConfig;
import com.hcdz.hk.config.SchoolAndPortConfig;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscribeServiceImpl implements SubscribeService{
	/**
	 * 学校回应isc平台地址
	 */
	@Autowired
	private SchoolAndPortConfig maps;
	/**
	 * 学校对应事件订阅号
	 */
	@Autowired
	private SchoolAndEventConfig eventMaps;
	
	@Override
	public String tryR(String schoolCodeId) {
		//从配置文件中获取isc平台地址、appkey、appSecret三个参数
		String[] ipAndKey = getIpAmdPort(schoolCodeId);

		String ipAmdPort = ipAndKey[0];
		if(ipAmdPort==null) {
			throw new ServiceException("事件订阅请求地址为空，请检查学校订阅平台配置");
		}
		ArtemisConfig.host = ipAmdPort; // artemis网关服务器ip端口
		ArtemisConfig.appKey = ipAndKey[1];  // 秘钥appkey
		ArtemisConfig.appSecret = ipAndKey[2];// 秘钥appSecret
		//STEP2：设置OpenAPI接口的上下文
		final String ARTEMIS_PATH = "/artemis";
		//STEP3：设置接口的URI地址
		final String previewURLsApi = ARTEMIS_PATH + "/api/frs/v1/application/picture";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
			}
		};
		//STEP4：设置参数提交方式
		String contentType = "application/json";
		// STEP5：组装请求参数
		JSONObject body = new JSONObject();
		body.put("url", "http://192.168.3.229:80/picture/Streaming/tracks/103/?name=ch0001_00000004351031725107200049243&size=49243");
		//STEP6：调用接口
		String result = ArtemisHttpUtil.doPostStringArtemis(path, body.toJSONString(), null, null, contentType, null);// post请求application/json类型参数
		return result;

	}

	/**
	 * 订阅事件
	 */
	@Override
	public String eventSubscriptions(String schoolCodeId) {
		//从配置文件中获取isc平台地址、appkey、appSecret三个参数
		String[] ipAndKey = getIpAmdPort(schoolCodeId);

		String ipAmdPort = ipAndKey[0];
		if(ipAmdPort==null) {
			throw new ServiceException("事件订阅请求地址为空，请检查学校订阅平台配置");
		}
		ArtemisConfig.host = ipAmdPort; // artemis网关服务器ip端口
		ArtemisConfig.appKey = ipAndKey[1];  // 秘钥appkey
		ArtemisConfig.appSecret = ipAndKey[2];// 秘钥appSecret
		//STEP2：设置OpenAPI接口的上下文
		final String ARTEMIS_PATH = "/artemis";
		//STEP3：设置接口的URI地址
		final String previewURLsApi = ARTEMIS_PATH + "/api/eventService/v1/eventSubscriptionByEventTypes";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
			}
		};
		//STEP4：设置参数提交方式
		String contentType = "application/json";
		// STEP5：组装请求参数
		JSONObject jsonBody = new JSONObject();
		List<Long> eventList = getEventList(schoolCodeId);
		if(eventList==null) {
			throw new ServiceException("订阅事件为空，请检查学校事件订阅类型");
		}
		jsonBody.put("eventTypes", eventList);
		jsonBody.put("eventDest", ipAndKey[3]);
		//jsonBody.put("subType", 2);
	//	jsonBody.put("eventlv1", 2);
		String body = jsonBody.toJSONString();
		//STEP6：调用接口
		String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);// post请求application/json类型参数
		return result;

	}

	/**
	 * 按事件类型取消订阅事件
	 */
	@Override
	public String UnsubscriptionEvent(String schoolCodeId) {
		//从配置文件中获取isc平台地址、appkey、appSecret三个参数
		String[] ipAndKey = getIpAmdPort(schoolCodeId);
		ArtemisConfig.host = ipAndKey[0]; // artemis网关服务器ip端口
		ArtemisConfig.appKey = ipAndKey[1];  // 秘钥appkey
		ArtemisConfig.appSecret = "CQoxj4OvL42w4rzAyPUU";// 秘钥appSecret
		//STEP2：设置OpenAPI接口的上下文
		final String ARTEMIS_PATH = "/artemis";
		//STEP3：设置接口的URI地址
		final String previewURLsApi = ARTEMIS_PATH + "/api/eventService/v1/eventUnSubscriptionByEventTypes";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
			}
		};
		//STEP4：设置参数提交方式
		String contentType = "application/json";
		// STEP5：组装请求参数
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("eventTypes", getEventList(schoolCodeId));
		String body = jsonBody.toJSONString();
		//STEP6：调用接口
		String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);// post请求application/json类型参数
		return result;

	}
	/**
	 * 查看此服务器订阅的事件
	 */
	@Override
	public String viewSubscriptionEvents(String schoolCodeId) {
		//从配置文件中获取isc平台地址、appkey、appSecret三个参数
		String[] ipAndKey = getIpAmdPort(schoolCodeId);

		ArtemisConfig.host = ipAndKey[0]; // artemis网关服务器ip端口
		ArtemisConfig.appKey = ipAndKey[1];  // 秘钥appkey
		ArtemisConfig.appSecret = ipAndKey[2];// 秘钥appSecret
		//STEP2：设置OpenAPI接口的上下文
		final String ARTEMIS_PATH = "/artemis";
		//STEP3：设置接口的URI地址
		final String previewURLsApi = ARTEMIS_PATH + "/api/eventService/v1/eventSubscriptionView";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
			}
		};
		//STEP4：设置参数提交方式
		String contentType = "application/json";
		//STEP6：调用接口
		String result = ArtemisHttpUtil.doPostStringArtemis(path, null, null, null, contentType, null);// post请求application/json类型参数
		return result;
	}


	/**获取学校订阅的Isc平台地址
	 * 
	 * @param schoolCodeId
	 * @return
	 */
	public String[] getIpAmdPort(String schoolCodeId) {
		Map<String, String> schoolCodeMap = maps.getSchoolCode();
		String[] split = schoolCodeMap.get(schoolCodeId).split(";");
		return split;
	}

	/**获取学校订阅事件集合
	 * 
	 * @param schoolCodeId
	 * @return
	 */
	public List<Long> getEventList(String schoolCodeId){
		Map<String, String> schoolCoddeMap1 = eventMaps.getSchoolCodde();
		String events = schoolCoddeMap1.get(schoolCodeId);
		String[] split = events.split(",");
		List<Long> eventTypesList = new  ArrayList<Long>();
		for (int i = 0; i < split.length; i++) {
			eventTypesList.add(Long.valueOf(split[i]));
		}
		return eventTypesList;
	}
}

