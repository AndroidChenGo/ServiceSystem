package cn.application;

import cn.domain.model.Contract;
import cn.domain.resposity.ClientRepository;
import cn.domain.resposity.ContractRepository;
import cn.domain.resposity.FreelancerRepository;
import cn.domain.resposity.ProjectRepository;
import cn.infrastructure.repositoryImpl.ClientRepositoryImpl;
import cn.infrastructure.repositoryImpl.ContractRepositoryImpl;
import cn.infrastructure.repositoryImpl.FreelancerRepositoryImpl;
import cn.infrastructure.repositoryImpl.ProjectRepositoryImpl;
import cn.infrastructure.util.WebUtils;

import java.util.List;

public class ContractService {
    private ContractRepository contractRepository = new ContractRepositoryImpl();
    private ClientRepository clientRepository = new ClientRepositoryImpl();
    private ProjectRepository projectRepository = new ProjectRepositoryImpl();
    private FreelancerRepository freelancerRepository = new FreelancerRepositoryImpl();
    public List<Contract> queryContracts() {
        return contractRepository.getAllContracts();
    }

    public Contract queryContractById(Integer id) {
        return contractRepository.getContractById(id);
    }
    public List<Contract> queryContractByFreelancerId(Integer freelancerId) {
        return contractRepository.getContractByFreelancerId(freelancerId);
    }
    public List<Contract> queryContractByClientId(Integer clientId) {
        return contractRepository.getContractByClientId(clientId);
    }
    public List<Contract> queryContractByProjectId(Integer projectId) {
        return contractRepository.getContractByProjectId(projectId);
    }
    public List<Contract> queryContractByState(String state) {
        return contractRepository.getContractByState(state);
    }
    public List<Contract> queryContractByName(String name) {
        return contractRepository.getContractByName(name);
    }
    public boolean saveContract(Contract contract) {
        if(clientRepository.getClientById(contract.getClientId()) == null){
            return false;
        }
        if(!WebUtils.isIntEmpty(contract.getProjectId()) && projectRepository.getProjectById(contract.getProjectId()) == null)
            return false;
        if(!WebUtils.isIntEmpty(contract.getFreelancerId()) && freelancerRepository.getFreelancerById(contract.getFreelancerId()) == null)
            return false;
        return contractRepository.saveContract(contract);
    }
    public void deleteContractById(Integer id) {
        contractRepository.removeContractById(id);
    }
}
