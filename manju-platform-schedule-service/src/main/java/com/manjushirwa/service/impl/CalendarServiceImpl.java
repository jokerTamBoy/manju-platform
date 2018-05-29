package com.manjushirwa.service.impl;


import com.manjushirwa.mapper.schdule.CalendarMapper;
import com.manjushirwa.service.CalendarService;
import org.springframework.stereotype.Service;
import com.manjushirwa.pojo.schedule.po.Calendar;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Resource
    private CalendarMapper calendarMapper;


    @Override
    public Calendar getCalendar(String id) {
        return calendarMapper.selectById(id);
    }

    @Override
    public void addCalendar(Calendar calendar) {
        calendarMapper.insert(calendar);
    }

    @Override
    public void updateCalendar(Calendar calendar) {
        calendarMapper.updateById(calendar);
    }

    @Override
    public void delete(Calendar calendar) {
        calendarMapper.delete(calendar);
    }

    @Override
    public List<Calendar> getCalendarsByMap(Map<String, Object> record) {

        return calendarMapper.selectByMap(record);
    }

}