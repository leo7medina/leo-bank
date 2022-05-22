package ec.com.leo.bank.controller;

import ec.com.leo.bank.common.LeoBankUtil;
import ec.com.leo.bank.model.Account;
import ec.com.leo.bank.model.Client;
import ec.com.leo.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cuentas")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/client/{idClient}")
    public ResponseEntity<?> findAccountByClient(@PathVariable Integer idClient) {
        List<Account> listAccount;
        Map<String, Object> response = new HashMap<>();
        try{
            listAccount = accountService.findByClient(idClient);
        } catch (DataAccessException e) {
            response.put("mensaje", "No se encontraron cuentas existentes en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listAccount, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody Account account, BindingResult result) {
        Account accountNew = null;
        Map<String, Object> response = new HashMap<>();
        LeoBankUtil.checkErrors(result, response);
        try {
            accountNew = accountService.savaAccount(account);
            response.put("success", "La cuenta ha sido creado con éxito!");
            response.put("cuenta", accountNew);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody Account account, BindingResult result, @PathVariable Integer id) {
        Account accountCurrent = accountService.findAccount(id);
        Account accountUpdate = null;
        Map<String,Object> response = new HashMap<>();
        LeoBankUtil.checkErrors(result, response);
        if(accountCurrent == null) {
            response.put("mensaje", "Error: no se pudo editar, la cuenta ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            accountCurrent.setTypeAccount(account.getTypeAccount());
            accountCurrent.setBalanceInitial(account.getBalanceInitial());
            accountUpdate = accountService.updateAccount(accountCurrent);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al actualizar el insert en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La cuenta ha sido actualizada con éxito!");
        response.put("cliente", accountUpdate);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            accountService.deleteAccount(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al eliminar la cuenta de la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("success", "La cuenta ha sido eliminado con éxito!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
