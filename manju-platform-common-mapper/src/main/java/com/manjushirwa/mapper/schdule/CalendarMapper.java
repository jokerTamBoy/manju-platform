package com.manjushirwa.mapper.schdule;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.manjushirwa.pojo.schedule.po.Calendar;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CalendarMapper extends BaseMapper<Calendar> {
    /**
     * 增加事件
     */
    Integer insert(Calendar calendar);
    /**
     * 删除事件
     */
    Integer delete(Calendar calendar);
    /**
     * 修改事件
     */
    Integer updateById(Calendar calendar);
    /**
     * 根据id查询事件
     */
    Calendar selectById(Serializable id);
    /**
     * 根据条件查询事件
     */
    List<Calendar> selectByMap(Map<String, Object> columnMap);
    /**
     * 查询所有事件
     */
    List<Calendar> getAllByUserId(String userId);
    /**
     * 按条件查询事件
     */
    List<Calendar> getByMap(@Param("params") Map<String,String> params);
    /**
     * 根据userId删除事件
     */
    Integer deleteByUserId(String userId);
    /**
     * 修改事件
     */
    Integer updateCalendar(Calendar calendar);
    /**
     * 根据id删除事件
     */
    Integer delById(String id);
}
