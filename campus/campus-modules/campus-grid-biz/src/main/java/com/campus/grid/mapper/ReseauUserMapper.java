package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.ReseauUser;
import com.campus.grid.api.entity.ReseauVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hu
 * @date 2019-01-02 16:17:17
 */
public interface ReseauUserMapper extends BaseMapper<ReseauUser> {

	List<ReseauUser> findReseauUserByReseauId(@Param("reseauId")String reseauId);

	void editReseauUser (@Param("reseauVo") ReseauVo reseauVo);

	void editFireReseauUser(@Param("reseauVo")ReseauVo reseauVo);

	String getUserNameByUserId(@Param("userId")String userId);

	void updatePrimaryPrincipal(@Param("reseauId")String reseauId,@Param("userId") String userId,
								@Param("userName")String userName, @Param("responsiblyType")Integer responsiblyType);

	void  createReseauUser(ReseauUser reseauUser);

	void updateRemark(@Param("remark")String remark,@Param("reseauId") String reseauId,
								 @Param("responsiblyType")Integer responsiblyType);

	String getInfo(@Param("reseauId") String reseauId,  @Param("responsiblyType")Integer responsiblyType);

	List<ReseauUser> getByReseauIdAndUserId(@Param("reseauId")String reseauId, @Param("userId")String userId);
}
