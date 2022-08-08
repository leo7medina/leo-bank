package ec.com.leo.bank.service;

import ec.com.leo.bank.GeneralUtil;
import ec.com.leo.bank.exception.ApiException;
import ec.com.leo.bank.mapper.ClientMapper;
import ec.com.leo.bank.model.ClientEntity;
import ec.com.leo.bank.model.PersonEntity;
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
    }

    @Override
    public ClientVO saveClient(ClientVO clientVO) {
        ClientEntity clientEntity = mapper.toClientEntity(clientVO);

        PersonEntity personEntity = personRepository.findByIdentification(clientVO.getIdentification());
        if (Objects.nonNull(personEntity)) {
            throw new ApiException("Identificacion ya existe");
        }

        PersonEntity personEntityNew = personRepository.save(clientEntity.getPersonEntity());
        if (Objects.nonNull(personEntityNew.getIdPerson())) {
            clientEntity.setIdPerson(personEntityNew.getIdPerson());
            clientEntity.getPersonEntity().setIdPerson(personEntityNew.getIdPerson());
            ClientEntity entityNew = clientRepository.save(clientEntity);
            return mapper.toClientVO(entityNew);
        } else {
            throw new RuntimeException("Error al guardar entidad persona");
        }
    }

    @Override
    public void updateClient(ClientVO clientEntity) {
        clientRepository.save(Objects.requireNonNull(GeneralUtil.convert(clientEntity, ClientEntity.class)));
        personRepository.save(GeneralUtil.getPersonEntityOfClient(clientEntity));
    }

    @Override
    public void deleteClient(Integer id) {
        ClientEntity clientEntity = clientRepository.findByIdClient(id);
        clientRepository.deleteById(id);
        if (Objects.nonNull(clientEntity)) {
            personRepository.deleteById(clientEntity.getIdPerson());
        }

    }

    @Override
    public void inactiveClient(Integer id) {
        ClientEntity clientEntity = clientRepository.getById(id);
        clientEntity.setStatus(Boolean.FALSE);
        clientRepository.save(clientEntity);
    }
}
