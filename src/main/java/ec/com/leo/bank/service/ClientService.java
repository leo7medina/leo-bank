package ec.com.leo.bank.service;

import ec.com.leo.bank.GeneralUtil;
import ec.com.leo.bank.mapper.ClientMapper;
import ec.com.leo.bank.model.ClientEntity;
import ec.com.leo.bank.repository.IClientRepository;
import ec.com.leo.bank.repository.IPersonRepository;
import ec.com.leo.bank.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class ClientService implements IClientService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Lazy
    @Autowired
    private ClientMapper mapper;


    @Override
    public List<ClientEntity> findClients() {
        return clientRepository.findAllActive();
    }

    @Override
    public ClientVO findClientById(Integer id) {
        ClientEntity clientEntity = clientRepository.findByIdClient(id);
        return mapper.toClientVO(clientEntity);
        //return GeneralUtil.convert(clientEntity, ClientVO.class);
        //return clientRepository.findByIdClient(id, ClientVO.class);
    }

    @Override
    public ClientVO saveClient(ClientVO clientEntity) {
        //personRepository.findByIdentification(clientEntity.getIdentification());
        ClientEntity client = clientRepository.save(Objects.requireNonNull(GeneralUtil.convert(clientEntity, ClientEntity.class)));
        return mapper.toClientVO(client);
        //return GeneralUtil.convert(client, ClientVO.class);
    }

    @Override
    public void updateClient(ClientVO clientEntity) {
        clientRepository.save(Objects.requireNonNull(GeneralUtil.convert(clientEntity, ClientEntity.class)));
    }

    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void inactiveClient(Integer id) {
        ClientEntity clientEntity = clientRepository.getById(id);
        clientEntity.setStatus(Boolean.FALSE);
        clientRepository.save(clientEntity);
    }
}
