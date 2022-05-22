package ec.com.leo.bank.controller;

import ec.com.leo.bank.common.LeoBankUtil;
import ec.com.leo.bank.model.Client;
import ec.com.leo.bank.service.IClientService;
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

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> findAll() {
        return clientService.findClients();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Client cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clientService.findClientById(id);
        } catch(DataAccessException e) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(cliente == null) {
            response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody Client client, BindingResult result) {
        Client clientNew = null;
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
    public ResponseEntity<?> update(@Validated @RequestBody Client client, BindingResult result, @PathVariable Integer id) {
        Client clientCurrent = clientService.findClientById(id);
        Client clientUpdate = null;
        Map<String,Object> response = new HashMap<>();
        LeoBankUtil.checkErrors(result, response);
        if(clientCurrent == null) {
            response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            /*clientCurrent.setName(client.getName());
            clientCurrent.setGender(client.getGender());
            clientCurrent.setAge(client.getAge());
            //clientCurrent.setIdentification(client.getIdentification());
            clientCurrent.setAddress(client.getAddress());
            clientCurrent.setPhone(client.getPhone());*/
            clientCurrent.setPassword(client.getPassword());
            clientUpdate = clientService.saveClient(clientCurrent);
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
