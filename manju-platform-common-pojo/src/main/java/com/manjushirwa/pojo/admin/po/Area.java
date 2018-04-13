/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushirwa.pojo.admin.po;

import com.manjushirwa.pojo.base.TreeEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 区域Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Data
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;
	private Area parent;	// 父级编号
	private String parentIds; // 所有父级编号
	private String code; 	// 区域编码
	private String name; 	// 区域名称
	private Integer sort;		// 排序
	private String type; 	// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
	
	public Area(){
		super();
		this.sort = 30;
	}

	@Override
	protected Serializable pkVal() {
		return super.id;
	}
}