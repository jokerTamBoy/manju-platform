package com.manjushirwa.pojo.schedule.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.manjushirwa.pojo.base.ScheduleEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("Calendar")

public class Calendar extends ScheduleEntity<Calendar> implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableField(value = "userId")
    private  String userId;
    @TableField(value = "title")
    private  String title;
    @TableField(value = "content")
    private  String content;
    @TableField(value = "startTime")
    private  String startTime;
    @TableField(value = "endTime")
    private  String endTime;
    @TableField(value = "urlId")
    private  String urlId;
    @TableField(value = "sourceId")
    private  String sourceId;
    @TableField(value = "createTime")
    private  String createTime;
    @TableField(value = "edit")
    private  int edit;

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

   public Calendar(String t, String u){
        title=t;
        userId=u;
        content="";
        startTime=String.valueOf(new Date().getTime());
        endTime=startTime;
        urlId="";
        sourceId="";
        createTime=startTime;
        edit=0;
    }

    @TableField(value = "editTime")
    private  String editTime;
    @Override
    protected Serializable pkVal() {
        return super.id;
    }
}
