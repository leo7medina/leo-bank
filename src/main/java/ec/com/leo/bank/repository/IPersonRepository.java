package ec.com.leo.bank.repository;

import ec.com.leo.bank.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Integer> {

    @Query(value = "SELECT p FROM PERSONA p WHERE p.identification = ?1", nativeQuery = false)
    PersonEntity findByIdentification(String identification);
}
