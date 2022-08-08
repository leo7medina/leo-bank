package ec.com.leo.bank.service;

import ec.com.leo.bank.common.TypeMovement;
import ec.com.leo.bank.exception.ApiException;
import ec.com.leo.bank.model.AccountEntity;
import ec.com.leo.bank.model.MovementEntity;
import ec.com.leo.bank.repository.IAccountRepository;
import ec.com.leo.bank.repository.IMovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class MovementService implements IMovementService {

    @Autowired
    private IAccountRepository accountRepository;

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
        Double saldo = 0D;

        List<MovementEntity> movFound = movementsRepository.findByIdAccount(entity.getIdAccount());
        if (Objects.isNull(movFound) || movFound.size() == 0) {
            AccountEntity accountEntityFound = accountRepository.findById(entity.getIdAccount()).orElse(new AccountEntity());
            saldo = accountEntityFound.getBalanceInitial().doubleValue();
        } else {
            //ultimos saldo disponible
            Integer idMovementLast = movementsRepository.findMaxByIdAccount(entity.getIdAccount());
            MovementEntity movementLast = movementsRepository.findById(idMovementLast).orElse(null);
            if (movementLast == null ) {
                throw new ApiException("Error consultando los movimientos");
            }
            //MovementEntity movementLast = movementsRepository.findLastByIdAccount(entity.getIdAccount());
            saldo = movementLast.getBalance();
        }

        if (entity.getTypeMovement().equals(TypeMovement.CREDIT.getValue())) {
            entity.setBalance(saldo + entity.getValue());
        } else if (entity.getTypeMovement().equals(TypeMovement.DEBIT.getValue())) {
            if (saldo.compareTo(0D) < 0) {
                throw new ApiException("Saldo insuficiente");
            }
            Double saldoNew = saldo - entity.getValue();
            if (saldoNew < 0D) {
                throw new ApiException("Saldo insuficiente");
            }
            entity.setBalance(saldoNew);
        }
        return movementsRepository.save(entity);
    }

    @Override
    public void deleteMovement(Integer id) {
        movementsRepository.deleteById(id);
    }


    public void findByClientAndDate(Integer idClient, Date startDate, Date endDate) {

    }
}
