package cn.infrastructure.repositoryImpl;

import cn.domain.model.Freelancer;
import cn.domain.resposity.FreelancerRepository;
import cn.infrastructure.util.StringUtil;

import java.util.List;

public class FreelancerRepositoryImpl extends BaseRespository implements FreelancerRepository {
    @Override
    public boolean saveFreelancer(Freelancer freelancer) {
        if(!StringUtil.isNotEmpty(freelancer.getUsername())){
            String username = StringUtil.getRandomString(10);
            String sql ="insert into freelancer(`username`,`id`,`name`,`type`,`phone`,`email`,`wechat`,`introduction`) values(?,?,?,?,?,?,?,?)";
            if(-1 != update(sql, username,freelancer.getId(),freelancer.getName(),freelancer.getType(),
                    freelancer.getPhone(),freelancer.getEmail(),freelancer.getWechat(),freelancer.getIntroduction()))
                return true;
            else return false;
        }else{
            String sql = "update freelancer set `id`=?,`name`=?,`type`=?,`phone`=?,`email`=?,`wechat`=?,`introduction`=? where username = ?";
            if(-1 != update(sql,freelancer.getId(),freelancer.getName(),freelancer.getType(),
                    freelancer.getPhone(),freelancer.getEmail(),freelancer.getWechat(),freelancer.getIntroduction(),freelancer.getUsername())){
                return true;
            }else return false;
        }
    }

    @Override
    public int removeFreelancerById(Integer id) {
        String sql = "delete from freelancer where id = ?";
        return update(sql, id);
    }

    @Override
    public List<Freelancer> getAllFreelancers() {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction` from freelancer";
        return queryForList(Freelancer.class, sql);
    }

    @Override
    public Freelancer getFreelancerById(Integer id) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction` from freelancer where id = ?";
        return queryForOne(Freelancer.class, sql,id);
    }

    @Override
    public List<Freelancer> getFreelancerByType(String serviceType) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from freelancer where type = ?";
        return queryForList(Freelancer.class, sql,serviceType);
    }

    @Override
    public List<Freelancer> getFreelancerByName(String name) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from freelancer where name = ?";
        return queryForList(Freelancer.class, sql,name);
    }

    @Override
    public Freelancer getFreelancerByUsername(String username) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from freelancer where username = ?";
        return queryForOne(Freelancer.class, sql,username);
    }

}
