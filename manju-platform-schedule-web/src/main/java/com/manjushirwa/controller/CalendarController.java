package com.manjushirwa.controller;

import com.manjushirwa.pojo.schedule.po.AjaxDataEntity;
import com.manjushirwa.pojo.schedule.po.Calendar;
import com.manjushirwa.pojo.schedule.po.CalendarDTO;
import com.manjushirwa.pojo.schedule.po.EditVO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        return "index2";
    }
    /**
     * 跳转到新增页
     * @param model
     * @return
     */
    @RequestMapping("/add")

    public String add(Model model){

        return "add";
    }


    /**
     * 获取所有事件
     */
    @RequestMapping("/list")
    @ResponseBody
    public AjaxDataEntity getAll(@RequestParam String userId){
        Map<String,String> params=new HashMap<>();

        params.put("user_id",userId);
        List<Calendar> list=calendarService.getByMap(params);
//        model.addAttribute("calendars",list);
        return AjaxDataEntity.success().add("events",list);
    }

    /**
     * 根据id获取事件
     */
    @RequestMapping("/getById")
    public String getCalendarById(String id,Model model){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Map<String,String> params=new HashMap<>();

        params.put("id",id);
        Calendar ca=calendarService.getByMap(params).get(0);
        EditVO calendarVo=new EditVO();
        calendarVo.setUserId(ca.getUserId());
        calendarVo.setStartTime(sdf.format((Long.parseLong(ca.getStartTime()))));
        calendarVo.setEndTime(sdf.format((Long.parseLong(ca.getEndTime()))));
        calendarVo.setId(ca.getId());
        calendarVo.setTitle(ca.getTitle());
        calendarVo.setContent(ca.getContent());
        model.addAttribute("calendar",calendarVo);
        return "edit";
    }

    /**
     * 增加事件
     */
    @RequestMapping("/doAdd")
    public String addCalendar( CalendarDTO calendarDTO, Model model) throws ParseException {
        logger.info(calendarDTO.getTitle());
        Calendar calendar=new Calendar();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date st=sdf.parse(calendarDTO.getStartTime());
        Date et=sdf.parse(calendarDTO.getEndTime());
//        Map<String,String> record=new HashMap<>();
//        record.put("id",id);
//      record.put("title",calendarDTO.getTitle());
//        if(calendarService.getByMap(record).size()>0){
//            return AjaxDataEntity.fail("事件id已经有了，傻吊！");
//        }
        calendar.setUrlId("test");
        calendar.setContent("test");
        calendar.setCreateTime(String.valueOf(new Date().getTime()));
        calendar.setEdit(1);
        calendar.setEditTime(String.valueOf(new Date().getTime()));
        calendar.setEndTime(String.valueOf(et.getTime()));
        calendar.setSourceId("sourceId");
        calendar.setStartTime(String.valueOf(st.getTime()));
        calendar.setUrlId("urlId");
        calendar.setContent(calendarDTO.getContent());
        calendar.setTitle(calendarDTO.getTitle());
        calendar.setUserId(calendarDTO.getUserId());
        calendar.setAllDay(calendarDTO.getAllDay());
        calendarService.addCalendar(calendar);
        return "/index";
    }

    /**
     * 删除事件
     */
    @RequestMapping("/delete")
    public String deleteCalendar(String id){
        calendarService.deleteByUserId(id);
        return "/index";
    }
    /**
     * 修改事件
     */
    @RequestMapping("/updateCalendar")
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
