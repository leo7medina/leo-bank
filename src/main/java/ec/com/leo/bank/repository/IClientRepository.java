package ec.com.leo.bank.repository;

import ec.com.leo.bank.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Query(value = "SELECT c FROM CLIENTE c WHERE c.status = true", nativeQuery = false)
    List<ClientEntity> findAllActive();
    //<T> T findByIdClient(Integer idClient, Class<T> type);

    @Query(value = "SELECT C, P FROM CLIENTE C inner join PERSONA P ON P.idPerson = C.idPerson WHERE C.idClient = ?1")
    ClientEntity findByIdClient(Integer idClient);
}
