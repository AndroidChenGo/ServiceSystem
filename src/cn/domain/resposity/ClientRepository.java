package cn.domain.resposity;

import cn.domain.model.Client;

import java.util.List;


public interface ClientRepository {
    /**
     * 添加或修改客户
     */
    boolean saveClient(Client client);

    /**
     * 根据删除客户信息
     */
    int removeClientById(Integer id);

    /**
     * 查询所有的自由职业者
     */
    List<Client> getAllClients();

    /**
     * 根据 id 查询指定的客户
     */
    Client getClientById(Integer id);

    /**
     * 根据 name 查询客户
     */
    List<Client> getClientByName(String name);

    /**
     * 根据 username 查询指定的客户
     */
    Client getClientByUsername(String username);

    /**
     * 根据 clientType 查询客户
     */
    List<Client> getClientByType(String clientType);

}
