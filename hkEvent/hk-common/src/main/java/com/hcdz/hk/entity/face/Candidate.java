package com.hcdz.hk.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
	/**
	 * 比对到的人脸所属人脸分组的唯一标识
	 * 注：和平台的人脸分组的标识不一定相同
	 */
    private String blacklist_id;
    /**
     * 人员数据
     */
    private List<Human_data> human_data;
    /**
     * 比对到的人脸所属人脸的唯一标识
     * 注：和平台的人脸分组的标识不一定相同
     */
    private String human_id;
    
    private Reserve_field reserve_field;
    /**
     * 	比对到的人脸和抓拍到的人脸之间的相似度
     */
    private Double similarity;

}