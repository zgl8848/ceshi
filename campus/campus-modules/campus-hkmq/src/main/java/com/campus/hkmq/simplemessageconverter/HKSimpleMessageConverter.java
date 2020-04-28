//package com.campus.hkmq.simplemessageconverter;
//
//import org.springframework.jms.support.converter.MessageConversionException;
//import org.springframework.jms.support.converter.SimpleMessageConverter;
//
//import javax.jms.BytesMessage;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import java.io.UnsupportedEncodingException;
//
///**
// * @author eatheryu
// * 简单的消息转换器
// */
//public class HKSimpleMessageConverter extends SimpleMessageConverter {
//	@Override
//	public Object fromMessage(Message msg) throws JMSException, MessageConversionException {
//		BytesMessage bmsg = (BytesMessage) msg;
//		byte[] bytes = new byte[(int) bmsg.getBodyLength()];
//		bmsg.readBytes(bytes);
//		String result = "";
//		try {
//			result = new String(bytes, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
//}
