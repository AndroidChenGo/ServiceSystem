package cn.domain.resposity;

import cn.domain.model.Contract;
import cn.domain.model.Project;

import java.util.List;

public interface ContractRepository {
    /**
     * 添加或修改合同
     */
    boolean saveContract(Contract contract);

    /**
     * 根据删除合同信息
     */
    int removeContractById(Integer id);

    /**
     * 查询所有的合同
     */
    List<Contract> getAllContracts();

    /**
     * 查询客户所有的合同
     */
    List<Contract> getContractByClientId(Integer clientId);

    /**
     * 根据 id 查询指定的合同
     */
    Contract getContractById(Integer id);

    /**
     * 根据 name 查询合同
     */
    List<Contract> getContractByName(String name);

    /**
     * 根据 state 查询合同
     */
    List<Contract> getContractByState(String state);

    /**
     * 根据 freelancerID 查询指定的合同
     */
    List<Contract> getContractByFreelancerId(Integer freelancerId);

    /**
     * 根据 projectId 查询指定的合同
     */
    List<Contract> getContractByProjectId(Integer projectId);

    /**
     * 根据 state 查询自由职业者合同
     */
    List<Contract> getFreelancerContractByState(Integer freelancerID,String state);

    /**
     * 根据 state 查询客户合同
     */
    List<Contract> getClientContractByState(Integer clientID,String state);

}
