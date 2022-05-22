package ec.com.leo.bank.service;

import ec.com.leo.bank.model.Client;

import java.util.List;

public interface IClientService {

    List<Client> findClients();

    Client findClientById(Integer id);

    Client saveClient(Client clientEntity);

    void updateClient(Client clientEntity);

    void deleteClient(Integer id);

    void inactiveClient(Integer id);

}
