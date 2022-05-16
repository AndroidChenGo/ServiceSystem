package cn.application;

import cn.domain.model.Freelancer;
import cn.domain.resposity.FreelancerRepository;
import cn.infrastructure.repositoryImpl.FreelancerRepositoryImpl;
import cn.infrastructure.util.StringUtil;

import java.util.List;
import java.util.Objects;

public class FreelancerService {
    private FreelancerRepository freelancerRepository = new FreelancerRepositoryImpl();
    public List<Freelancer> queryFreelancers() {
        return freelancerRepository.getAllFreelancers();
    }

    public Freelancer queryFreelancerById(Integer id) {
        return freelancerRepository.getFreelancerById(id);
    }
    public Freelancer queryFreelancerByUsername(String username) {
        return freelancerRepository.getFreelancerByUsername(username);
    }
    public List<Freelancer> queryFreelancerByType(String type) {
        return freelancerRepository.getFreelancerByType(type);
    }
    public List<Freelancer> queryFreelancerByName(String name) {
        return freelancerRepository.getFreelancerByName(name);
    }
    public boolean saveFreelancer(Freelancer freelancer) {
        Freelancer c = queryFreelancerById(freelancer.getId());
        if(c!=null && !StringUtil.isNotEmpty(freelancer.getUsername())){
            return false;
        }else if(c != null){
            if(!Objects.equals(c.getUsername(), freelancer.getUsername())){
                return false;
            }
        }
        return freelancerRepository.saveFreelancer(freelancer);
    }
    public void deleteFreelancerById(Integer id) {
        freelancerRepository.removeFreelancerById(id);
    }
}
