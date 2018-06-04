package com.manjushirwa.controller;

import com.manjushirwa.pojo.schedule.po.AjaxDataEntity;
import com.manjushirwa.pojo.schedule.po.Calendar;
import com.manjushirwa.pojo.schedule.po.CalendarDTO;
import com.manjushirwa.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CalendarController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @Resource
    private CalendarService calendarService;

    /**
     * 跳转到主页
     * @param model
     * @return
     */
    @RequestMapping("/index")

    public String index(Model model){

        return "index";
    }

    /**
     * 获取所有事件
     */
    @RequestMapping("/calendar/list")
    @ResponseBody
    public AjaxDataEntity getAll(@RequestParam String userId){
        Map<String,String> params=new HashMap<>();

        params.put("user_id",userId);
        List<Calendar> list=calendarService.getByMap(params);
//        model.addAttribute("calendars",list);
        return AjaxDataEntity.success().add("calendars",list);
    }
    /**
     * 增加事件
     */
    @RequestMapping("/calendar/add")
    @ResponseBody
    public AjaxDataEntity addCalendar(@RequestParam String params, Model model){
          logger.info(params);
          return null;
//        String  userId="test";
//        Calendar calendar=new Calendar();
//        Map<String,String> record=new HashMap<>();
//        record.put("user_id",userId);
//        record.put("title",title);
//        if(calendarService.getByMap(record).size()>0){
//            return AjaxDataEntity.fail("事件主题已经有了，傻吊！");
//        }
//        calendar.setContent("test");
//        calendar.setCreateTime(String.valueOf(new Date().getTime()));
//        calendar.setEdit(1);
//        calendar.setEditTime(String.valueOf(new Date().getTime()));
//        calendar.setEndTime(String.valueOf(new Date().getTime()));
//        calendar.setSourceId("sourceId");
//        calendar.setStartTime(String.valueOf(new Date().getTime()));
//        calendar.setUrlId("urlId");
//        calendar.setTitle(title);
//        calendar.setUserId(userId);
//        calendar.setAllDay(1);
//        calendarService.addCalendar(calendar);
//        return AjaxDataEntity.success().add("calendar",calendar);
    }

    /**
     * 删除事件
     */
    @RequestMapping("/calendar/delete")
    public String deleCalendar(String calendarId){
        calendarService.delete(calendarService.getCalendar(calendarId));
        return "index2";
    }
    /**
     * 修改事件
     */
    @RequestMapping("/calendar/update")
    @ResponseBody
    public AjaxDataEntity updateCalendar(@RequestBody CalendarDTO calendarDTO){
        Map<String,String> record=new HashMap<>();
        record.put("user_id",calendarDTO.getUserId());
        record.put("title",calendarDTO.getTitle());
        if(calendarService.getByMap(record).size()>1){
            return AjaxDataEntity.fail("数据重复了，傻吊！");
        }else if(calendarService.getByMap(record).size()<1){
            return AjaxDataEntity.fail("没有该事件，傻吊！");
        }
        Calendar calendar1=calendarService.getByMap(record).get(0);
        calendar1.setStartTime(calendarDTO.getStartTime());
        calendar1.setEndTime(calendarDTO.getEndTime());
        calendar1.setEditTime(calendarDTO.getEditTime());
        calendarService.updateCalendar(calendar1);
        return AjaxDataEntity.success();
    }
}
