package com.globalhitts.serviaseo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalhitts.serviaseo.entity.Client;
import com.globalhitts.serviaseo.repository.ClienteRepository;

@Service
public class ClientService {

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClientService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Optional<Client> findClienteByTipoDocumentoAndNumeroDocumento(int tipoDocumento, int numeroDocumento){
		return clienteRepository.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
	}

	public Optional<Client> findClienteById(int idCliente) {
		return clienteRepository.findByIdCliente(idCliente);
	}

	public Optional<Client> verificarCliente(int tipoDocumento, int numeroDocumento) {
		String resultado = clienteRepository.verificarCliente(tipoDocumento, numeroDocumento);
		return "Cliente existe".equals(resultado) ? Optional.of(new Client()) : Optional.empty();
	}
	@Transactional
	public Client registrarCliente(Client cliente) {
		clienteRepository.registrarCliente(cliente.getTipoDocumento(), cliente.getNumeroDocumento(),
				cliente.getNombreCliente(), cliente.getEmail(), cliente.getCelular(), cliente.getEstado());
		return cliente;
	}
}
