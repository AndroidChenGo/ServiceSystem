package cn.infrastructure.repositoryImpl;

import cn.domain.model.Contract;
import cn.domain.resposity.ContractRepository;
import cn.infrastructure.util.StringUtil;

import java.util.List;

public class ContractRepositoryImpl extends BaseRespository implements ContractRepository {

    @Override
    public boolean saveContract(Contract contract) {
        if(0 == contract.getId()){
            String id = StringUtil.getRandomNumberString(6);;
            while(getContractById(Integer.parseInt(id)) != null){
                id = StringUtil.getRandomString(10);
            }
            String sql ="insert into contract(`id`,`name`,`content`,`state`,`clientId`,`freelancerId`,`projectId`)  values(?,?,?,?,?,?,?)";
            if(-1 != update(sql, Integer.parseInt(id),contract.getName(),contract.getContent(),
                    contract.getState(),contract.getClientId(),contract.getFreelancerId(),contract.getProjectId()))
                return true;
            else return false;
        }else{
            String sql = "update contract set `name`=?,`content`=?,`state`=?,`clientId`=?,`freelancerId`=?,`projectId`=? where id = ?";
            if(-1 != update(sql,contract.getName(),contract.getContent(),
                    contract.getState(),contract.getClientId(),contract.getFreelancerId(),contract.getProjectId())){
                return true;
            }else return false;
        }
    }

    @Override
    public int removeContractById(Integer id) {
        String sql = "delete from contract where id = ?";
        return update(sql, id);
    }

    @Override
    public List<Contract> getAllContracts() {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract";
        return queryForList(Contract.class, sql);
    }

    @Override
    public List<Contract> getContractByClientId(Integer clientId) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where clientId = ?";
        return queryForList(Contract.class, sql,clientId);
    }

    @Override
    public Contract getContractById(Integer id) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where id = ?";
        return queryForOne(Contract.class, sql,id);
    }

    @Override
    public List<Contract> getContractByName(String name) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where name = ?";
        return queryForList(Contract.class, sql,name);
    }

    @Override
    public List<Contract> getContractByState(String state) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where state = ?";
        return queryForList(Contract.class, sql,state);
    }

    @Override
    public List<Contract> getContractByFreelancerId(Integer freelancerId) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where freelancerId = ?";
        return queryForList(Contract.class, sql,freelancerId);
    }

    @Override
    public List<Contract> getContractByProjectId(Integer projectId) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where projectId = ?";
        return queryForList(Contract.class, sql,projectId);
    }

    @Override
    public List<Contract> getFreelancerContractByState(Integer freelancerId, String state) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where freelancerId = ? and state = ?";
        return queryForList(Contract.class, sql,freelancerId,state);
    }

    @Override
    public List<Contract> getClientContractByState(Integer clientId, String state) {
        String sql = "select `id` , `name` , `state` , `content` , `clientId` , `freelancerId` , `projectId` from contract where clientId = ? and state = ?";
        return queryForList(Contract.class, sql,clientId,state);
    }

}
