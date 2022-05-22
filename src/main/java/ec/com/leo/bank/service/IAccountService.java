package ec.com.leo.bank.service;

import ec.com.leo.bank.model.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findByClient(Integer idClient);

    Account findAccount(Integer id);

    Account savaAccount(Account entity);

    Account updateAccount(Account entity);

    void deleteAccount(Integer id);

}
