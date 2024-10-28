package com.globalhitts.serviaseo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.globalhitts.serviaseo.entity.Client;
import com.globalhitts.serviaseo.service.ClientService;

@RestController
@RequestMapping("/clientes")

public class ClientController {

    private ClientService clienteService;

    @Autowired
    @Qualifier("clientService")
    public void setClienteService(ClientService clienteService) {
        this.clienteService = clienteService;
    }
    @RequestMapping(method = RequestMethod.GET)


    @PostMapping("/registrar")
    public ResponseEntity<Client> registrarCliente(@RequestBody Client cliente) {
        Client nuevoCliente = clienteService.registrarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/verificar/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<String> verificarCliente(@PathVariable int tipoDocumento, @PathVariable int numeroDocumento) {
        Optional<Client> cliente = clienteService.verificarCliente(tipoDocumento, numeroDocumento);
        return cliente.isPresent() ? new ResponseEntity<>("Client does not exist", HttpStatus.OK)
                : new ResponseEntity<>("The client is not registered", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<String> findClienteByTipoDocumentoAndNumeroDocumento(@PathVariable int tipoDocumento, @PathVariable int numeroDocumento) {
        Optional<Client> cliente = clienteService.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
        return cliente.isPresent() ? new ResponseEntity<>(cliente.toString(), HttpStatus.OK)
                : new ResponseEntity<>("The client is not registered", HttpStatus.NOT_FOUND);
    }
}
