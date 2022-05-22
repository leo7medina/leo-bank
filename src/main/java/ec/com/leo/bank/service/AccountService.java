package ec.com.leo.bank.service;

import ec.com.leo.bank.model.Account;
import ec.com.leo.bank.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> findByClient(Integer idClient) {
        return null;//accountRepository.fin
    }

    @Override
    public Account findAccount(Integer id) {
        return accountRepository.getById(id);
    }

    @Override
    public Account savaAccount(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Account updateAccount(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }


}
