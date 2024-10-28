package com.globalhitts.serviaseo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.globalhitts.serviaseo.DTO.DetalleFacturaDTO;
import com.globalhitts.serviaseo.entity.Invoice;
import com.globalhitts.serviaseo.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository facturaRepository;

	@Transactional
	public void crearFactura(int idCliente, Invoice factura, String productos) {
		facturaRepository.crearFactura(idCliente, factura.getIdLimpieza(), productos);
	}

	public List<String> listarFactura(int idFactura) {
		return facturaRepository.listarFactura(idFactura);
	}
	
	public List<String> listarFacturas() {
		return facturaRepository.listarFacturas();
	}
}