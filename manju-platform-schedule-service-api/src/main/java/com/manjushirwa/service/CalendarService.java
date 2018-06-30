package com.manjushirwa.service;


import com.manjushirwa.pojo.schedule.po.Calendar;
import java.util.List;
import java.util.Map;

public interface CalendarService {
    Calendar getCalendar(String id);
    void addCalendar(Calendar calendar);
    void updateCalendar(Calendar calendar);
    void delete(Calendar calendar);
    List<Calendar> getCalendarsByMap(Map<String,Object> record);
    List<Calendar> getAllByUserId(String userId);
    List<Calendar> getByMap( Map<String,String> params);
    void delById(String id);
}
