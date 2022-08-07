package ec.com.leo.bank.repository;

import ec.com.leo.bank.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Query(value = "SELECT c from CUENTA c where c.idClient = ?1", nativeQuery = false)
    List<AccountEntity> findByIdClient(Integer idClient);

    @Query(value = "SELECT c from CUENTA c where c.number = ?1")
    AccountEntity findByNumber(String number);

}
