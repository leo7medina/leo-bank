package ec.com.leo.bank.controller;

import ec.com.leo.bank.common.LeoBankUtil;
import ec.com.leo.bank.exception.ApiException;
import ec.com.leo.bank.model.AccountEntity;
import ec.com.leo.bank.model.MovementEntity;
import ec.com.leo.bank.service.IMovementService;
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
@RequestMapping("/api/movimientos")
public class MovementsController {

    @Autowired
    private IMovementService movementService;

    @GetMapping("/{idMovement}")
    public ResponseEntity<?> findMovementById(@PathVariable Integer idMovement) {
        Map<String, Object> response = new HashMap<>();
        MovementEntity movementEntity = null;
        try{
            movementEntity = movementService.findMovement(idMovement);
        } catch (ApiException e) {
            response.put("mensaje", "No se encontro movimiento existente en la base de datos.");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(movementEntity, HttpStatus.OK);
    }

    @GetMapping("/cuenta/{idAccount}")
    public ResponseEntity<?> findAccountByClient(@PathVariable Integer idAccount) {
        List<MovementEntity> listAccount;
        Map<String, Object> response = new HashMap<>();
        try{
            listAccount = movementService.findByAccount(idAccount);
        } catch (ApiException e) {
            response.put("mensaje", "No se encontraron movimientos existentes en la base de datos.");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listAccount, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody MovementEntity movement, BindingResult result) {
        MovementEntity movementNew = null;
        Map<String, Object> response = new HashMap<>();
        LeoBankUtil.checkErrors(result, response);
        try {
            movementNew = movementService.savaMovement(movement);
            response.put("success", "El movimiento ha sido creado con éxito!");
            response.put("cuenta", movementNew);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(ApiException e) {
            response.put("mensaje", "Error al realizar el registro del movimiento. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessageDefinition()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            movementService.deleteMovement(id);
        }catch(ApiException e) {
            response.put("mensaje", "Error al eliminar el movimiento de la base de datos. ");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("success", "El movimiento ha sido eliminado con éxito!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
