package cn.domain.domainService;

import cn.domain.model.Contract;

public interface ContractSignDomainService {

//    ClientRespositoryImpl clientRepository;
//    ProjectRepositoryImpl projectRepository;
//    ContractRepositoryImpl ContractRepository;
//    FreelancerRepositoryImpl freelancerRepository;

    public Contract sign(String freelancerID, String projectID, String clientID);

}
