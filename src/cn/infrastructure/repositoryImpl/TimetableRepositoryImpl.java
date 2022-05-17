package cn.infrastructure.repositoryImpl;

import cn.domain.model.Timetable;
import cn.domain.resposity.TimetableRepository;
import cn.infrastructure.util.StringUtil;

import java.sql.Timestamp;
import java.util.List;

public class TimetableRepositoryImpl extends BaseRespository implements TimetableRepository {
    @Override
    public boolean saveTimetable(Timetable timetable) {
        if(0 == timetable.getId()){
            String id = StringUtil.getRandomNumberString(6);;
            while(getTimetableById(Integer.parseInt(id)) != null){
                id = StringUtil.getRandomNumberString(10);
            }
            String sql ="insert into timetable(`id`,`name`,`startTime`,`endTime`,`freelancerId`,`content`) values(?,?,?,?,?,?)";
            if(-1 != update(sql, Integer.parseInt(id),timetable.getName(),timetable.getStartTime(),timetable.getEndTime(),
                    timetable.getFreelancerId(),timetable.getContent()))
                return true;
            else return false;
        }else{
            String sql = "update timetable set `name`=?,`startTime`=?,`endTime`=?,`content`=?,`freelancerId`=? where id = ?";
            if(-1 != update(sql,timetable.getName(),timetable.getStartTime(),timetable.getEndTime(),
                    timetable.getContent(),timetable.getFreelancerId(),timetable.getId())){
                return true;
            }else return false;
        }
    }

    @Override
    public int removeTimetableById(Integer id) {
        String sql = "delete from timetable where id = ?";
        return update(sql, id);
    }

    @Override
    public List<Timetable> getAllTimetables() {
        String sql = "select `id`,`name`,`startTime`,`endTime`,`freelancerId`,`content` from timetable";
        return queryForList(Timetable.class, sql);
    }

    @Override
    public Timetable getTimetableById(Integer id) {
        String sql = "select `id` , `name` , `startTime`,`endTime`,`freelancerId`,`content` from timetable where id = ?";
        return queryForOne(Timetable.class, sql,id);
    }

    @Override
    public List<Timetable> getTimetableByFreelancerId(Integer freelancerId) {
        String sql = "select `id` , `name` , `startTime`,`endTime`,`freelancerId`,`content` from timetable where freelancerId = ?";
        return queryForList(Timetable.class, sql,freelancerId);
    }

    @Override
    public List<Timetable> getTimetableByName(String name) {
        String sql = "select `id` , `name` , `startTime`,`endTime`,`freelancerId`,`content` from Timetable where name = ?";
        return queryForList(Timetable.class, sql,name);
    }

    @Override
    public List<Timetable> getTimetableByTime(Timestamp time) {
        String sql = "select `id` , `name` , `startTime`,`endTime`,`freelancerId`,`content` from Timetable where ? between startTime and endTime";
        return queryForList(Timetable.class, sql,time);
    }

    @Override
    public List<Timetable> getTimetableByContent(String content) {
        StringBuilder sql = new StringBuilder("select `id` , `name` , `startTime`,`endTime`,`freelancerId`,`content` from Timetable where content like '%");
        sql.append(content).append("%'");
        return queryForList(Timetable.class, sql.toString());
    }
}
