package com.globalhitts.serviaseo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.globalhitts.serviaseo.DTO.DetalleFacturaDTO;
import com.globalhitts.serviaseo.entity.Factura;
import com.globalhitts.serviaseo.repository.FacturaRepository;

@Service
public class FacturaService {
	@Autowired
	private FacturaRepository facturaRepository;

	@Transactional
	public void crearFactura(int idCliente,Factura factura, String productos) {
		facturaRepository.crearFactura(idCliente, factura.getIdLimpieza(), productos);
	}

	public List<String> listarFactura(int idFactura) {
		return facturaRepository.listarFactura(idFactura);
	}
	
	public List<String> listarFacturas() {
		return facturaRepository.listarFacturas();
	}
}