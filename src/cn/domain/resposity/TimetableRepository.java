package cn.domain.resposity;

import cn.domain.model.Timetable;

import java.sql.Timestamp;
import java.util.List;

public interface TimetableRepository {
    /**
     * 添加或修改时间表
     */
    boolean saveTimetable(Timetable timetable);

    /**
     * 根据删除时间表信息
     */
    int removeTimetableById(Integer id);

    /**
     * 查询所有的时间表
     */
    List<Timetable> getAllTimetables();

    /**
     * 根据 id 查询指定的时间表
     */
    Timetable getTimetableById(Integer id);

    /**
     * 根据 freelancerId 查询指定的时间表
     */
    List<Timetable> getTimetableByFreelancerId(Integer freelancerId);

    /**
     * 根据 name 查询时间表
     */
    List<Timetable> getTimetableByName(String name);

    /**
     * 根据 time 查询时间表
     */
    List<Timetable> getTimetableByTime(Timestamp time);

    /**
     * 根据 content 查询时间表
     */
    List<Timetable> getTimetableByContent(String state);
}
