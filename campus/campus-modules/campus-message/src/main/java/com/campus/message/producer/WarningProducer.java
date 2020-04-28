package com.campus.message.producer;

import com.campus.grid.api.entity.equipmententity.EquipmentMsgSynchronize;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
public class WarningProducer {

	private static int xl = 3;
	private static int x = 0;
	@Autowired
	private KafkaTemplate kafkaTemplate;
	//初始化gson
	private Gson gson = new Gson();

	@GetMapping("/send")
	public String send() {
//String msg="123"+"平台名称"+"设备名称"+"1"+"127.0.0.1"+"8088"+"admin"+"123456"+"0"+"备注"+"1234567542234";
//		for (int xx=x;x<xl;x++){
//	Equipment equipment = new Equipment("123","平台名称","设备名称","1","127.0.0.1","8088","admin","123456","0","备注","1234567542234","北京","dawdwa");
//			equipment.setEquipmentId(String.valueOf(x));
//			String s = gson.toJson(equipment);
//			ListenableFuture test2 = kafkaTemplate.send("testWriter", ""+x,s);

		EquipmentMsgSynchronize equipmentMsgSynchronize = new EquipmentMsgSynchronize("" + x++, "1", "视频汇聚", "123456", "groupid", "groupName", "equipmentName", "add", "equipmentIp", "8088", "piston", "00000002", "username", "password", "data:image/jpeg;base64,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", "1", "remark");
		String s = gson.toJson(equipmentMsgSynchronize);
		//进行信息的发送
		kafkaTemplate.send("testWriter3", s);
//		}
		xl += 3;
		return "success";
	}
}
