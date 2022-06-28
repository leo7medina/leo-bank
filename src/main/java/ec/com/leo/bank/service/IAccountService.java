package ec.com.leo.bank.service;

import ec.com.leo.bank.model.AccountEntity;

import java.util.List;

public interface IAccountService {

    List<AccountEntity> findByClient(Integer idClient);

    AccountEntity findAccount(Integer id);

    AccountEntity savaAccount(AccountEntity entity);

    AccountEntity updateAccount(AccountEntity entity);

    void deleteAccount(Integer id);

}
