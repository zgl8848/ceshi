package com.campus.grid.api.entity.hktonglinkmsg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class EventNotify implements Serializable {
	private String event_log_id;    //	报警事件日志ID
	private String event_type;        //报警事件类型
	private String status;        //报警事件状态 0 瞬时，1 脉冲开始，2 脉冲持续，3 脉冲结束
	private String start_time;        //报警事件开始时间
	private String stop_time;        //报警事件停止时间.可为空
	private String event_config_id;    //	报警事件配置ID
	private String event_name;        //报警事件名称
	private String event_level;        //报警事件配置级别. 1 高，2 中，3 低
	private String object_type;        //报警点对象类型
	private String object_index_code;//		报警点对象索引号
	private String object_name;        //报警点对象名称
	private String org_index;        //报警对象组织索引
	private String org_name;        //报警对象组织名称
	private Describe describe;        //报警事件描述信息
	private String pic_data;        //图片地址 uuid
	private String dataType;        //数据类型 1-设备信息 2-告警信息
	private String platformIp;        //平台ip
	private String platformPort;        //平台port
}
