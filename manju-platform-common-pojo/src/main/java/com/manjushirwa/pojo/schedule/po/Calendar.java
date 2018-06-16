package com.manjushirwa.pojo.schedule.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.manjushirwa.pojo.base.ScheduleEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Calendar")

public class Calendar extends ScheduleEntity<Calendar> implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableField(value = "user_id")
    private  String userId;
    @TableField(value = "title")
    private  String title;
    @TableField(value = "content")
    private  String content;
    @TableField(value = "start_time")
    private  String startTime;
    @TableField(value = "end_time")
    private  String endTime;
    @TableField(value = "url_id")
    private  String urlId;
    @TableField(value = "source_id")
    private  String sourceId;
    @TableField(value = "create_time")
    private  String createTime;
    @TableField(value = "edit")
    private  int edit;

    public int getAllDay() {
        return allDay;
    }

    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    @TableField(value = "all_day")
    private int allDay;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    @TableField(value = "edit_time")
    private  String editTime;

    @Override
    protected Serializable pkVal() {
        return super.id;
    }
}
