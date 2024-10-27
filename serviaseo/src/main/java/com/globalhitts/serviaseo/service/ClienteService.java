package com.globalhitts.serviaseo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalhitts.serviaseo.entity.Cliente;
import com.globalhitts.serviaseo.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Optional<Cliente> findClienteByTipoDocumentoAndNumeroDocumento(int tipoDocumento, int numeroDocumento){
		return clienteRepository.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
	}

	public Optional<Cliente> findClienteById(int idCliente) {
		return clienteRepository.findByIdCliente(idCliente);
	}

	public Optional<Cliente> verificarCliente(int tipoDocumento, int numeroDocumento) {
		String resultado = clienteRepository.verificarCliente(tipoDocumento, numeroDocumento);
		return "Cliente existe".equals(resultado) ? Optional.of(new Cliente()) : Optional.empty();
	}
	@Transactional
	public Cliente registrarCliente(Cliente cliente) {
		clienteRepository.registrarCliente(cliente.getTipoDocumento(), cliente.getNumeroDocumento(),
				cliente.getNombreCliente(), cliente.getEmail(), cliente.getCelular(), cliente.getEstado());
		return cliente;
	}
}
