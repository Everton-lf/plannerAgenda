package com.everton.planner.dto;


import com.everton.planner.DayOfWeekEnum.DayOfWeekEnum;

public class ScheduleEntryDTO {

    public Long id;
    public DayOfWeekEnum dayOfWeek;
    public String timeRange;
    public String description;
    public boolean completed;
}