package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author hlp
 * @date 2019-05-17 10:22:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_appversion")
public class SysAppversion extends Model<SysAppversion> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    /**
   * 版本号
   */
    private String code;
    /**
   * 类别 0-全量更新 1-打补丁
   */
    private String versionType;
    /**
   * 类别 0-用户端 1-商户端
   */
    private String versionTag;
    /**
   * 状态 0停用 1开启
   */
    private String versionStatus;
    /**
   * 下载地址
   */
    private String downUrl;
    /**
   * 文件大小
   */
    private String fileSize;
    /**
   * md5 加密
   */
    private String md5;
    /**
   * 修改内容
   */
    private String vsersionDesc;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
  
}
