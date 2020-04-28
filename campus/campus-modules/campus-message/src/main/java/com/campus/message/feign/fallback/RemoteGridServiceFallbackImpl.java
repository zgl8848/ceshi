package com.campus.message.feign.fallback;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.equipmententity.EquipmentMsgSynchronize;
import com.campus.message.feign.RemoteGridService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author campu
 */
@Slf4j
@Component
public class RemoteGridServiceFallbackImpl implements RemoteGridService {
	@Setter
	private Throwable cause;

	@Override
	public R saveEquipmentMsg(EquipmentMsgSynchronize equipment, String from) {
		log.error("feign 插入设备信息数据失败:{}", equipment, cause);
		return null;
	}

	@Override
	public String getSchoolIdByCode(String schoolCode, String from) {
		log.error("feign 查询设备信息学校ID失败:{}", schoolCode, cause);
		return null;
	}

	@Override
	public R updateEquipmentMsg(EquipmentMsgSynchronize equipment, String from) {
		log.error("feign 更改设备信息失败:{}", equipment, cause);
		return null;
	}

	@Override
	public R deleteEquipmentMsg(EquipmentMsgSynchronize equipment, String from) {
		log.error("feign 删除学校设备信息失败:{}", equipment, cause);
		return null;
	}


}
