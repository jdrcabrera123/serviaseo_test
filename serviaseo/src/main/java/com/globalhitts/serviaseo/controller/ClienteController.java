package com.globalhitts.serviaseo.controller;

import java.util.Optional;

import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.globalhitts.serviaseo.entity.Cliente;
import com.globalhitts.serviaseo.service.ClienteService;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    @Qualifier("clienteService")
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @RequestMapping(method = RequestMethod.GET)


    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.registrarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/verificar/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<String> verificarCliente(@PathVariable int tipoDocumento, @PathVariable int numeroDocumento) {
        Optional<Cliente> cliente = clienteService.verificarCliente(tipoDocumento, numeroDocumento);
        return cliente.isPresent() ? new ResponseEntity<>("Cliente existe", HttpStatus.OK)
                : new ResponseEntity<>("Cliente no existe", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<String> findClienteByTipoDocumentoAndNumeroDocumento(@PathVariable int tipoDocumento, @PathVariable int numeroDocumento) {
        Optional<Cliente> cliente = clienteService.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
        return cliente.isPresent() ? new ResponseEntity<>(cliente.toString(), HttpStatus.OK)
                : new ResponseEntity<>("Cliente no existe", HttpStatus.NOT_FOUND);
    }
}
