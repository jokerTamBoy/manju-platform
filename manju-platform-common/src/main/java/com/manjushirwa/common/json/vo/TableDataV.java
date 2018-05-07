package com.manjushirwa.common.json.vo;

import java.io.Serializable;
import java.util.List;

public class TableDataV implements Serializable {

    private List<Object> Data;
    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;

    public List<Object> getData() {
        return Data;
    }

    public void setData(List<Object> data) {
        Data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
