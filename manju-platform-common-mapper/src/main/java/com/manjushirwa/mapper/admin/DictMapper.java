package com.manjushirwa.mapper.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.manjushirwa.pojo.admin.po.Dict;

/**
 * 后台管理用户表 Mapper
 *
 * @author Administrator
 */
public interface DictMapper extends BaseMapper<Dict> {

   public Integer insertDict(Dict dict);
}
