package cn.infrastructure.repositoryImpl;

import cn.domain.model.Client;
import cn.domain.model.Project;
import cn.domain.resposity.ClientRepository;
import cn.infrastructure.util.StringUtil;

import java.util.List;

public class ClientRepositoryImpl extends BaseRespository implements ClientRepository {

    @Override
    public boolean saveClient(Client client) {
        if(!StringUtil.isNotEmpty(client.getUsername())){
            String username = StringUtil.getRandomString(10);
            String sql ="insert into client(`username`,`id`,`name`,`type`,`phone`,`email`,`wechat`,`introduction`) values(?,?,?,?,?,?,?,?)";
            if(-1 != update(sql, username,client.getId(),client.getName(),client.getType(),
                    client.getPhone(),client.getEmail(),client.getWechat(),client.getIntroduction()))
                return true;
            else return false;
        }else{
            String sql = "update client set `id`=?,`name`=?,`type`=?,`phone`=?,`email`=?,`wechat`=?,`introduction`=? where username = ?";
            if(-1 != update(sql,client.getId(),client.getName(),client.getType(),
                    client.getPhone(),client.getEmail(),client.getWechat(),client.getIntroduction(),client.getUsername())){
                return true;
            }else return false;
        }
    }

    @Override
    public int removeClientById(Integer id) {
        String sql = "delete from client where id = ?";
        return update(sql, id);
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction` from client";
        return queryForList(Client.class, sql);
    }

    @Override
    public Client getClientById(Integer id) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from client where id = ?";
        return queryForOne(Client.class, sql,id);
    }

    @Override
    public List<Client> getClientByName(String name) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from client where name = ?";
        return queryForList(Client.class, sql,name);
    }

    @Override
    public Client getClientByUsername(String username) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from client where username = ?";
        return queryForOne(Client.class, sql,username);
    }

    @Override
    public List<Client> getClientByType(String clientType) {
        String sql = "select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from client where type = ?";
        return queryForList(Client.class, sql,clientType);
    }

    @Override
    public List<Client> getClientByIntroduction(String introduction) {
        StringBuilder sql = new StringBuilder("select `id` , `name` , `type` , `phone` , `email` , `wechat` , `introduction`, `username` from client where introduction like '%");
        sql.append(introduction).append("%'");
        return queryForList(Client.class, sql.toString());
    }
}
