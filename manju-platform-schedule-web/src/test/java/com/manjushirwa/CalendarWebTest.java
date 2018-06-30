package com.manjushirwa;

import com.manjushirwa.pojo.schedule.po.Calendar;
import com.manjushirwa.service.CalendarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =ManjuPlatformScheduleWebApplication.class )
public class CalendarWebTest {
    private static final Logger log = LoggerFactory.getLogger(CalendarWebTest.class);


    @Resource
    private CalendarService calendarService;

    private String id;

    /**
     * 查询所有
     */
    @Test
    public void test5() {
        List<Calendar> list=  calendarService.getAllByUserId("test");
    }
    /**
     * 条件查询
     */
    @Test
    public void test6() {
        Map<String ,String > params=new HashMap<>();
        params.put("user_id","test");
        params.put(" content","test");
        List<Calendar> list=  calendarService.getByMap(params);
        System.out.println(list.size());
    }
    /**
     * 增
     */
    @Test
    public void test2() {
        Calendar calendar=new Calendar();
        calendar.setContent("test");
        calendar.setCreateTime("1526615219");
        calendar.setEdit(1);
        calendar.setEditTime("1526615219");
        calendar.setEndTime("1526615219");
        calendar.setSourceId("sourceId");
        calendar.setStartTime("1526615219");
        calendar.setUrlId("urlId");
        calendar.setTitle("test");
        calendar.setUserId("test");
        calendarService.addCalendar(calendar);

    }
    /**
     * 查
     */
    @Test
    public void test1() {
        Map<String,Object> record=new HashMap<>();
        Map<String,String> vl=new HashMap<>();
        vl.put("user_id","test");
        record.put("where",vl);
        List<Calendar> calendars=calendarService.getCalendarsByMap(record);
        Calendar calendar=calendars.get(0);
    }

    /**
     * 改
     */
    @Test
    public void test4() {
        Map<String,String> params=new HashMap<>();
        params.put("user_id","test");
        params.put("title","test");
        Calendar calendar=calendarService.getByMap(params).get(0);
        calendar.setContent("test123");
        calendar.setCreateTime("1526615219");
        calendar.setEdit(1);
        calendar.setEditTime("1526615219");
        calendar.setEndTime("1526615219");
        calendar.setSourceId("sourceId");
        calendar.setStartTime("1526615219");
        calendar.setUrlId("urlId");
        calendar.setTitle("test123");
        calendar.setUserId("test123");
        calendarService.updateCalendar(calendar);
    }
    /**
     * 删
     */
    @Test
    public void test3() {
        calendarService.deleteByUserId("test");
    }
}
