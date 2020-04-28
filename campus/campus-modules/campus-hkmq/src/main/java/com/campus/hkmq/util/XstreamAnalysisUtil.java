package com.campus.hkmq.util;

import com.campus.grid.api.entity.hktonglinkmsg.Describe;
import com.campus.grid.api.entity.hktonglinkmsg.DevInfo;
import com.campus.grid.api.entity.hktonglinkmsg.EventNotify;
import com.campus.grid.api.entity.hktonglinkmsg.ExtEventInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XstreamAnalysisUtil {


	public static EventNotify xmlToBean(String xml) {
		XStream xStream = null;
		try {
			log.info("开始解析xml:{}", xml);
			xStream = new XStream(new DomDriver("UTF-8")) {
				@Override
				protected MapperWrapper wrapMapper(MapperWrapper next) {
					return new MapperWrapper(next) {
						@Override
						public boolean shouldSerializeMember(Class definedIn, String fieldName) {
							if (definedIn == Object.class) {
								return false;
							}
							return super.shouldSerializeMember(definedIn, fieldName);
						}
					};
				}
			};
			XStream.setupDefaultSecurity(xStream);
			xStream.allowTypes(new Class[]{
					EventNotify.class, Describe.class, ExtEventInfo.class, DevInfo.class});
			xStream.alias("EventNotify", EventNotify.class);
			xStream.alias("describe", Describe.class);
			xStream.alias("ExtEventInfo", ExtEventInfo.class);
			xStream.alias("DevInfo", DevInfo.class);
			xStream.aliasField("ExtEventInfo", Describe.class, "extEventInfo");
			xStream.aliasField("DevInfo", ExtEventInfo.class, "devInfo");
			xStream.aliasField("DevIP", DevInfo.class, "devIp");
			return (EventNotify) xStream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("解析xml失败了,异常为:{}", e);
		}
		return null;
	}
}
