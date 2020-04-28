package com.campus.grid.api.dto;

import com.campus.admin.api.dto.UserDTO;
import com.campus.grid.api.entity.InspectGroup;
import lombok.Data;
import java.util.*;

@Data
public class InspectGroupDTO extends InspectGroup {
	/**
	 * 组长名称
	 */
	private String leaderName;
	/**
	 * 联系方式
	 */
	private String contact;
	/**
	 * 所属学校
	 */
	private String schoolName;
	/**
	 *区域内学校
	 */
	private List<UserDTO> underlingUser;
	/**
	 *	小组成员
	 */
	private List<UserDTO> groupMember;
	/**
	 * 小组成员集合
	 */
	private String groupMembers;
}
