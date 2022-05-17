package cn.domain.resposity;

import cn.domain.model.Client;
import cn.domain.model.Freelancer;

import java.util.List;

public interface FreelancerRepository {
    /**
     * 添加或修改自由职业者
     */
    boolean saveFreelancer(Freelancer freelancer);

    /**
     * 根据删除自由职业者信息
     */
    int removeFreelancerById(Integer id);

    /**
     * 查询所有的自由职业者
     */
    List<Freelancer> getAllFreelancers();

    /**
     * 根据 id 查询指定的自由职业者
     */
    Freelancer getFreelancerById(Integer id);

    /**
     * 根据 serviceType 查询自由职业者
     */
    List<Freelancer> getFreelancerByType(String serviceType);

    /**
     * 根据 name 查询自由职业者
     */
    List<Freelancer> getFreelancerByName(String name);

    /**
     * 根据 username 查询指定的自由职业者
     */
    Freelancer getFreelancerByUsername(String username);

    /**
     * 根据 introduction 查询自由职业者
     */
    List<Freelancer> getFreelancerByIntroduction(String introduction);

}
