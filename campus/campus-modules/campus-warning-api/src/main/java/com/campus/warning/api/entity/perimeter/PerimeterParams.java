package com.campus.warning.api.entity.perimeter;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerimeterParams {
	/**
	 * 事件类别
	 */
	private String ability;
	/**
     * 事件信息
     */
	private List<PerimeterEvents> events;
	/**
     * 事件从接收者（程序处理后）发出的时间
     */
	private String sendTime;

}