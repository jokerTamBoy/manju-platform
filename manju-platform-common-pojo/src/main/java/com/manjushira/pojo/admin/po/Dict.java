/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushira.pojo.admin.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.manjushira.pojo.base.DataEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Data
@TableName("sys_dict")
public class Dict extends DataEntity<Dict> {

	private static final long serialVersionUID = 1L;
	private String value;	// 数据值
	private String label;	// 标签名
	private String type;	// 类型
	private String description;// 描述
	private Integer sort;	// 排序
	@TableField(value="parent_id")
	private String parentId;//父Id

	public Dict() {
		super();
	}
	
	public Dict(String value, String label){
		this.value = value;
		this.label = label;
	}

	@Override
	protected Serializable pkVal() {
		return super.id;
	}

}