package com.apineoris.demoneoris.controller;

import com.apineoris.demoneoris.dto.ClienteDto;
import com.apineoris.demoneoris.entity.Cliente;
import com.apineoris.demoneoris.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    Logger log = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping("/")
    public List<ClienteDto> getAllClients() {
        List<ClienteDto> allClientes = service.getAllClientes();

        if (allClientes.isEmpty()) {
            log.warn("No se recuperó ningún cliente de la base de datos");
        }

        return allClientes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable(name = "id") long id) {
        ClienteDto clienteDto = service.getClienteById(id);

        if (clienteDto == null) {
            log.warn("No se encontró el cliente con id: {}", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clienteDto);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody Cliente cliente) {
        ClienteDto clienteDto = service.saveNewCliente(cliente);

        if (clienteDto == null) {
            log.warn("No se pudo guardar el nuevo cliente {}", cliente);
            return ResponseEntity.status(500).body(clienteDto);
        }

        return ResponseEntity.status(201).body(clienteDto);
    }

    //Put, Push, delete
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateWholeClienteById(@RequestBody Cliente cliente,
                                                             @PathVariable(name = "id") long id) {
        ClienteDto clienteDto = service.updateWholeClienteById(id);

        if (clienteDto == null) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.status(201).body(clienteDto);
    }

    @PatchMapping("/")
    public ClienteDto updatePartiallyCliente(@RequestBody Cliente cliente,
                                             @PathVariable(name = "id") long id) {
        ClienteDto clienteDto = service.updatePartiallyClienteById(id, cliente);
        return clienteDto;
    }

    @DeleteMapping("/{id}")
    public ClienteDto deleteCliente(@PathVariable(name = "id") long id) {
        ClienteDto clienteDto = service.deleteClienteById(id);
        return clienteDto;
    }

}
