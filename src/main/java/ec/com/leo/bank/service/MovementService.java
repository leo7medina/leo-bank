package ec.com.leo.bank.service;

import ec.com.leo.bank.exception.ApiException;
import ec.com.leo.bank.model.MovementEntity;
import ec.com.leo.bank.repository.IMovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService implements IMovementService {

    @Autowired
    private IMovementsRepository movementsRepository;

    @Override
    public MovementEntity findMovement(Integer idMovement) {
        return movementsRepository.findById(idMovement).orElse( null);
    }

    @Override
    public List<MovementEntity> findByAccount(Integer idAccount) {
        return movementsRepository.findByIdAccount(idAccount);
    }

    @Override
    public MovementEntity savaMovement(MovementEntity entity) {
        return movementsRepository.save(entity);
    }

    @Override
    public void deleteMovement(Integer id) {
        movementsRepository.deleteById(id);
    }
}
