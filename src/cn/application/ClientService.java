package cn.application;

import cn.domain.model.Client;
import cn.domain.resposity.ClientRepository;
import cn.infrastructure.repositoryImpl.ClientRepositoryImpl;
import cn.infrastructure.util.StringUtil;

import java.util.List;
import java.util.Objects;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepositoryImpl();
    public List<Client> queryClients() {
        return clientRepository.getAllClients();
    }

    public Client queryClientById(Integer id) {
        return clientRepository.getClientById(id);
    }
    public Client queryClientByUsername(String username) {
        return clientRepository.getClientByUsername(username);
    }
    public List<Client> queryClientByType(String type) {
        return clientRepository.getClientByType(type);
    }
    public List<Client> queryClientByName(String name) {
        return clientRepository.getClientByName(name);
    }
    public boolean saveClient(Client client) {
        Client c = queryClientById(client.getId());
        if(c!=null && !StringUtil.isNotEmpty(client.getUsername())){
            return false;
        }else if(c != null){
            if(!Objects.equals(c.getUsername(), client.getUsername())){
                return false;
            }
        }
        return clientRepository.saveClient(client);
    }
    public void deleteClientById(Integer id) {
        clientRepository.removeClientById(id);
    }
}
