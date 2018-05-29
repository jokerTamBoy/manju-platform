package com.manjushirwa.controller;

import com.manjushirwa.pojo.schedule.po.Calendar;
import com.manjushirwa.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CalendarController {
    private static final Logger _logger = LoggerFactory.getLogger(CalendarController.class);

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
    public String getAll(Model model){
        Map<String,Object> map=new HashMap<>();
        map.put("","");
        List<Calendar> list=calendarService.getCalendarsByMap(map);
        model.addAttribute("calendars",list);
        return "index";
    }

    /**
     * 增加事件
     */
    @RequestMapping("/calendar/add")
    public String addCalendar(String title,String userId){
        userId="test";
        calendarService.addCalendar(new Calendar(title,userId));
        return "index";
    }

    /**
     * 删除事件
     */
    @RequestMapping("/calendar/delete")
    public String deleCalendar(String calendarId){
        calendarService.delete(calendarService.getCalendar(calendarId));
        return "index";
    }
    /**
     * 修改事件
     */
//    @RequestMapping("/calendar/add")
//    public String updateCalendar(String calendarId){
//        calendarService.updateCalendar();
//        return "index";
//    }

}
