package com.campus.warning.api.entity.firecontrol;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireParams {
	 /**
     * 事件从接收者（程序处理后）发出的时间
     */
    private String  sendTime;
    /**
	 * 事件类别
	 */
    private String ability;
    /**
     * 用户id
     */
    private String[] uids;
    /**
     * 事件信息
     */
    private List<FireEvents> events;

}