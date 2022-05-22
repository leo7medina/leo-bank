package ec.com.leo.bank.service;

import ec.com.leo.bank.model.Client;
import ec.com.leo.bank.repository.IClientRepository;
import ec.com.leo.bank.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClientService implements IClientService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IClientRepository clientRepository;


    @Override
    public List<Client> findClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(Integer id) {
        return clientRepository.getById(id);
    }

    @Override
    public Client saveClient(Client clientEntity) {
        //personRepository.findByIdentification(clientEntity.getIdentification());
        return clientRepository.save(clientEntity);
    }

    @Override
    public void updateClient(Client clientEntity) {
        clientRepository.save(clientEntity);
    }

    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void inactiveClient(Integer id) {
        Client clientEntity = clientRepository.getById(id);
        clientEntity.setStatus(Boolean.FALSE);
        clientRepository.save(clientEntity);
    }
}
