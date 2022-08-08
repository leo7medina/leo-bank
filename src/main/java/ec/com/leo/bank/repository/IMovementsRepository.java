package ec.com.leo.bank.repository;

import ec.com.leo.bank.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovementsRepository extends JpaRepository<MovementEntity, Integer> {

    @Query("SELECT  m from MOVIMIENTO m where m.idAccount = ?1")
    List<MovementEntity> findByIdAccount(Integer idAccount);

    @Query(value = "SELECT TOP 1 m from MOVIMIENTO m where m.idAccount = ?1 ORDER BY m.idMovement desc", nativeQuery = true )
    MovementEntity findLastByIdAccount(Integer idAccount);
}
