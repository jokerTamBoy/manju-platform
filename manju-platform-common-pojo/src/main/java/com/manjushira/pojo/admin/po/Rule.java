package com.manjushira.pojo.admin.po;

import com.manjushira.pojo.base.DataEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Ciaoyen on 23/03/2018.
 */
@Data
public class Rule extends DataEntity<Rule> {
    private static final long serialVersionUID = 1L;
    /**
     * 权限名称
     */
    private String permission;
    /**
     * 权限说明
     */
    private String description;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
