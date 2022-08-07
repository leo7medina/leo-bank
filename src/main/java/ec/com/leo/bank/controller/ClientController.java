package ec.com.leo.bank.controller;

import ec.com.leo.bank.common.LeoBankUtil;
import ec.com.leo.bank.model.ClientEntity;
import ec.com.leo.bank.service.IClientService;
import ec.com.leo.bank.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientEntity> findAll() {
        return clientService.findClients();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        ClientVO client = null;
        Map<String, Object> response = new HashMap<>();
        try {
            client = clientService.findClientById(id);
        } catch(DataAccessException e) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(client == null) {
            response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(client,HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ClientVO client, BindingResult result) {
        ClientVO clientNew = null;
        Map<String, Object> response = new HashMap<>();
        LeoBankUtil.checkErrors(result, response);
        try {
            clientNew = clientService.saveClient(client);
            response.put("success", "El cliente ha sido creado con éxito!");
            response.put("cliente", clientNew);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody ClientVO client, BindingResult result, @PathVariable Integer id) {
        ClientVO clientCurrent = clientService.findClientById(id);
        ClientVO clientUpdate = null;
        Map<String,Object> response = new HashMap<>();
        LeoBankUtil.checkErrors(result, response);
        if(clientCurrent == null) {
            response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        client.setIdClient(clientCurrent.getIdClient());
        client.setIdPerson(clientCurrent.getIdPerson());
        try {
            clientService.updateClient(client);
            clientUpdate = clientService.findClientById(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al actualizar el insert en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido actualizado con éxito!");
        response.put("cliente", clientUpdate);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.deleteClient(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al eliminar el cliente de la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("success", "El cliente ha sido eliminado con éxito!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
