package ec.com.leo.bank.service;

import ec.com.leo.bank.exception.ApiException;
import ec.com.leo.bank.model.AccountEntity;
import ec.com.leo.bank.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<AccountEntity> findByClient(Integer idClient) {
        return accountRepository.findByIdClient(idClient);
    }

    @Override
    public AccountEntity findAccount(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public AccountEntity savaAccount(AccountEntity entity) {
        AccountEntity accountFound = accountRepository.findByNumber(entity.getNumber());
        if (Objects.nonNull(accountFound)) {
            throw new ApiException("El numero de cuenta ya existe en base.");
        }
        return accountRepository.save(entity);
    }

    @Override
    public AccountEntity updateAccount(AccountEntity entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }


}
