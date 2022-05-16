package cn.application;

import cn.domain.model.Timetable;
import cn.domain.resposity.FreelancerRepository;
import cn.domain.resposity.TimetableRepository;
import cn.infrastructure.repositoryImpl.FreelancerRepositoryImpl;
import cn.infrastructure.repositoryImpl.TimetableRepositoryImpl;
import cn.infrastructure.util.StringUtil;

import java.sql.Timestamp;
import java.util.List;

public class TimetableService {
    private TimetableRepository timetableRepository = new TimetableRepositoryImpl();
    private FreelancerRepository freelancerRepository = new FreelancerRepositoryImpl();
    public List<Timetable> queryTimetables() {
        return timetableRepository.getAllTimetables();
    }

    public Timetable queryTimetableById(Integer id) {
        return timetableRepository.getTimetableById(id);
    }
    public List<Timetable> queryTimetableByFreelancerId(Integer id) {
        return timetableRepository.getTimetableByFreelancerId(id);
    }
    public List<Timetable> queryTimetableByContent(String content) {
        return timetableRepository.getTimetableByContent(content);
    }
    public List<Timetable> queryTimetableByName(String name) {
        return timetableRepository.getTimetableByName(name);
    }
    public List<Timetable> queryTimetableByTime(String time) {
        Timestamp timestamp = StringUtil.changeStringToTimestamp(time);
        return timetableRepository.getTimetableByTime(timestamp);
    }
    public boolean saveTimetable(Timetable timetable) {
        if(freelancerRepository.getFreelancerById(timetable.getFreelancerId()) == null){
            return false;
        }
        return timetableRepository.saveTimetable(timetable);
    }
    public void deleteProjectById(Integer id) {
        timetableRepository.removeTimetableById(id);
    }
}
