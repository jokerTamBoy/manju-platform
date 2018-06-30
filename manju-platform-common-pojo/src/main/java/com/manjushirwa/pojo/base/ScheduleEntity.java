package com.manjushirwa.pojo.base;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

public class ScheduleEntity <T extends Model> extends Model<T> implements Serializable{
    private static final long serialVersionUID = 1L;


    public String getId() {
        return id;
    }

    /**
     * 主键ID
     */
    protected String id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
