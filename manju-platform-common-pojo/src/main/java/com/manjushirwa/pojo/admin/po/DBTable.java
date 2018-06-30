package com.manjushirwa.pojo.admin.po;


import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

public class DBTable extends Model implements Serializable {
    private String tableName;
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    protected Serializable pkVal() {
        return this.tableName;
    }
}
