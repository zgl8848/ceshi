package com.campus.grid.util;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.campus.admin.api.dto.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class InspectHiddenUtils {
	/**
	 * Date转换为LocalDateTime
	 *
	 * @param date
	 */
	public static LocalDateTime date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
		ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}

	/**
	 * LocalDateTime转换为Date
	 *
	 * @param localDateTime
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			ZoneId zoneId = ZoneId.systemDefault();
			ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
			Date date = Date.from(zdt.toInstant());
			return date;
		}
		return null;
	}

	/**
	 * String转换为LocalDateTime
	 */
	public static LocalDateTime string2LocalDateTime(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = format.parse(date);
			LocalDateTime localDateTime = InspectHiddenUtils.date2LocalDateTime(date1);
			return localDateTime;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * String转换为Date
	 */
	public static Date string2Date(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = format.parse(date);
			return date1;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Date转换为String再转为Date
	 */
	public static Date date2String(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = simpleDateFormat.format(date);
		System.out.println(format);
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(format);
			return parse;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 传入两个int数值 获取百分比结果
	 */
	public static String percentage(Integer total, Integer untreated) {

		/**
		 * getInstance() 方法说明：
		 * 返回当前默认语言环境的通用数字格式， 这和打电话一样。
		 *
		 * setMaximumFractionDigits(2) 方法说明：
		 * 保留小数点后的位数; 参数如果小于零，则使用零。
		 */
		if (total == 0) {
			return "0%";
		}
		java.text.NumberFormat numberFormat = java.text.NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2); // 保留小数点后两位

		String result = numberFormat.format((float) untreated / (float) total * 100);
		return result + "%";
	}

	/**
	 * 获取某月的第一天
	 */
	public static LocalDateTime getFirstDayOfMonth(String date) {

		String[] split = date.split("-");
		int year = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]);

		Calendar cal = Calendar.getInstance();

//设置年份

		cal.set(Calendar.YEAR, year);

//设置月份

		cal.set(Calendar.MONTH, month - 1);

//获取某月最大天数

		int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

//设置日历中月份的最大天数

		cal.set(Calendar.DAY_OF_MONTH, lastDay);

//格式化日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parse = sdf1.parse(lastDayOfMonth);
			LocalDateTime localDateTime = date2LocalDateTime(parse);
			return localDateTime;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取某月的最后一天
	 */
	public static LocalDateTime getLastDayOfMonth(String date) {
		String[] split = date.split("-");
		int year = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]);

		Calendar cal = Calendar.getInstance();

//设置年份

		cal.set(Calendar.YEAR, year);

//设置月份

		cal.set(Calendar.MONTH, month - 1);

//获取某月最大天数

		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

//设置日历中月份的最大天数

		cal.set(Calendar.DAY_OF_MONTH, lastDay);

//格式化日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String firstDayOfMonth = sdf.format(cal.getTime());

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parse = sdf1.parse(firstDayOfMonth);
			LocalDateTime localDateTime = date2LocalDateTime(parse);
			return localDateTime;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取两个月份之间的所有月份
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		try {
			min.setTime(sdf.parse(minDate));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

			max.setTime(sdf.parse(maxDate));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

			Calendar curr = min;
			while (curr.before(max)) {
				result.add(sdf.format(curr.getTime()));
				curr.add(Calendar.MONTH, 1);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得一个日期的后一天开始日期
	 */
	public static Date getAfterDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);

		Calendar c = Calendar.getInstance();

		try {

			date = new SimpleDateFormat("yy-MM-dd").parse(format);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		c.setTime(date);

		int day = c.get(Calendar.DATE);

		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = null;
		try {
			parse = sdf1.parse(dayAfter);
			return parse;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}

	/**
	 * 获得一个日期的后一天开始日期
	 */

	public static LocalDateTime getSpecifiedDayBefore(String specifiedDay){
		specifiedDay = specifiedDay.replace("GMT", "").replaceAll("\\(.*\\)", "");
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day);
		return InspectHiddenUtils.date2LocalDateTime(c.getTime());

	}

	public static List<String> arrayToList(List<String> list, String str) {
		String[] split = str.split(",");
		for (int i = 0; i < split.length; i++) {
			list.add(split[i]);
		}
		return list;
	}

	public static String listToString(List<String> list) {
		String newValues = "";
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				newValues = newValues + list.get(i) + ",";
			} else {
				newValues = newValues + list.get(i);
			}
		}
		return newValues;
	}


	/**
	 * 所有平台，推送目标是别名为 "registrationId"，通知内容为 msgContent
	 *
	 * @return
	 */
	public static String buildPushObjectAllAliasAalert(String masterSecret, String appKey, String registrationId, String msgContent) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
		PushPayload payload= PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(registrationId))
				.setNotification(Notification.alert(msgContent))
				.build();
		try {
			jpushClient.sendPush(payload);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 巡查和隐患查询添加日期格式的转换
	 */
	public static Map<String, LocalDateTime> shellDate(String date) {
		Map<String, LocalDateTime> map = new HashMap<>();
		if (date != null) {
			String[] split = date.split(",");
			if (split.length == 2) {

				String start = split[0];
				String end = split[1];
				start = start.replace("GMT", "").replaceAll("\\(.*\\)", "");
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH);
				try {
					LocalDateTime startTime = InspectHiddenUtils.date2LocalDateTime(sdf.parse(start));
					end = end.replace("GMT", "").replaceAll("\\(.*\\)", "");
					Date parse = sdf.parse(end);
					LocalDateTime endTime = InspectHiddenUtils.date2LocalDateTime(InspectHiddenUtils.getAfterDay(parse));
					map.put("startTime", startTime);
					map.put("endTime", endTime);
					return map;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		map.put("startTime", null);
		map.put("endTime", null);
		return map;
	}

	/**
	 * list去重
	 */
	public static List<UserDTO> getUserDTO(List<UserDTO> underlingUser,List<UserDTO> groupMember,String userId){
		List<UserDTO> newList = new ArrayList<UserDTO>();
		boolean flag = false;
		for (UserDTO oldUserDTO : underlingUser) {
			for (UserDTO newUserDTO : groupMember) {
				if (newUserDTO.getUserId().equals(oldUserDTO.getUserId())) {
					flag = true;
					break;
				}else {
					flag =false;
				}
			}
			if (!flag) {
				newList.add(oldUserDTO);
			}
		}
		for (int i = 0; i < newList.size(); i++) {
			if (newList.get(i).getUserId().equals(userId)){
				newList.remove(i);
				break;
			}
		}

		return newList;
	}
}
