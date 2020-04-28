package com.campus.warning.api.entity.face;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identify {
	/**
	 * 比对到的目标人脸
	 * 注：有几个对象代表比对到了几张人脸
	 */
    private List<Candidate> candidate;
    /**
     * 比对到的人脸所属人脸分组的唯一标识
     * 注：和平台的人脸分组的标识不一定相同
     */
    private Double maxsimilarity;

}