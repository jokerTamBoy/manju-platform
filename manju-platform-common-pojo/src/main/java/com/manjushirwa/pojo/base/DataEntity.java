/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushirwa.pojo.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.manjushirwa.pojo.admin.po.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Data
public abstract class DataEntity<T extends Model> extends Model<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	protected String id;
	protected String remarks;	// 备注
	@TableField(value="create_by",exist = false)
	protected User createBy;	// 创建者
	@TableField(value="create_date",exist = false)
	@JsonFormat(locale="zh", timezone="GMT+0", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date createDate;	// 创建日期
	@TableField(value="update_by",exist = false)
	protected User updateBy;	// 更新者
	@TableField(value="update_date",exist = false)
	@JsonFormat(locale="zh", timezone="GMT+0", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;	// 更新日期
	@TableField(value="del_flag",exist = false)
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}
