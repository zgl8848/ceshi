package com.campus.message.utils;

import com.campus.grid.api.entity.equipmententity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author eatheryu
 */
@Component
@AllArgsConstructor
@Data
public class SaveMsgListUtils {

	/**
	 * 操作类型
	 */

	public static final String MSG_ADD = "add";
	public static final String MSG_UPDATE = "update";

	/**
	 * 数据类型
	 */

	public static final String MSG_EQUIPMENT = "1";
	/**
	 * 存储设备信息集合
	 */
	private volatile List addOperatingList = new ArrayList();
	private List updateOperatingList = new ArrayList();
	private List deleteOperatingList = new ArrayList();

	public List saveEquimentObject(Equipment equipment, String flag) {
		if ("add".equals(flag)) {
			this.addOperatingList.add(equipment);
			return getList(addOperatingList);
		} else if ("update".equals(flag)) {
			updateOperatingList.add(equipment);
			return getList(updateOperatingList);
		} else {
			deleteOperatingList.add(equipment);
			return getList(deleteOperatingList);
		}
	}


	public void clearListInfo() {
		this.addOperatingList.clear();
	}

	private ArrayList getList(List arr) {

		List list = new ArrayList();
		Iterator it = arr.iterator();
		while (it.hasNext()) {
			Object obj = (Object) it.next();
			if (!list.contains(obj)) {
				list.add(obj);
			}
		}
		return (ArrayList) list;
	}
}
