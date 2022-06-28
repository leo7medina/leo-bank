package ec.com.leo.bank.repository;

import ec.com.leo.bank.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovementsRepository extends JpaRepository<MovementEntity, Integer> {
}
