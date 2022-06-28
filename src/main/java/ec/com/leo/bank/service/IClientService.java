package ec.com.leo.bank.service;

import ec.com.leo.bank.model.ClientEntity;
import ec.com.leo.bank.vo.ClientVO;

import java.util.List;

public interface IClientService {

    List<ClientEntity> findClients();

    ClientVO findClientById(Integer id);

    ClientVO saveClient(ClientVO client);

    void updateClient(ClientVO client);

    void deleteClient(Integer id);

    void inactiveClient(Integer id);

}
