package ec.com.leo.bank.service;

import ec.com.leo.bank.model.MovementEntity;

import java.util.List;

public interface IMovementService {

    MovementEntity findMovement(Integer idMovement);

    List<MovementEntity> findByAccount(Integer idAccount);

    MovementEntity savaMovement(MovementEntity entity);

    void deleteMovement(Integer id);
}
