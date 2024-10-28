package com.globalhitts.serviaseo.controller;

import com.globalhitts.serviaseo.DTO.InvoiceDetailDTO;
import com.globalhitts.serviaseo.entity.Client;
import com.globalhitts.serviaseo.entity.Invoice;
import com.globalhitts.serviaseo.service.ClientService;
import com.globalhitts.serviaseo.service.EmailService;
import com.globalhitts.serviaseo.service.InvoiceService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas")
public class InvoiceController {

	private final ClientService clienteService;
	private final InvoiceService facturaService;
	private final EmailService emailService;

	public InvoiceController(ClientService clienteService, InvoiceService facturaService, EmailService emailService) {
		this.clienteService = clienteService;
		this.facturaService = facturaService;
		this.emailService = emailService;
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crearFactura(@RequestBody Invoice factura) {
		int tipoDocumento = factura.getTipoDocumento();
		int numeroDocumento = factura.getNumeroDocumento();
		Optional<Client> clienteOpt = clienteService.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento,
				numeroDocumento);
		if (clienteOpt.isPresent()) {
			Client cliente = clienteOpt.get();
			int idCliente = cliente.getIdCliente();

			String productosJson = new Gson().toJson(factura.getProductos());

			facturaService.crearFactura(idCliente, factura, productosJson);

			String toEmail = cliente.getEmail();
			String subject = "Invoice Created - ID: " + factura.getIdFactura();
			String body = "An invoice has been successfully generated..\n\n" + "Invoice detail:\n" + "user ID: "
					+ idCliente + "\n" + "ID Clean: " + factura.getIdLimpieza() + "\n" + "Products: "
					+ productosJson;

			emailService.enviarFactura(toEmail, subject, body);

			return new ResponseEntity<>("Invoice generated and sent to customer.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("The user does not exist, please register the user.", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("buscar/{idFactura}")
	public InvoiceDetailDTO obtenerFactura(@PathVariable int idFactura) {
		InvoiceDetailDTO dto = new InvoiceDetailDTO();
		List<String> facturas = facturaService.listarFactura(idFactura);
		String facturaCompleta = facturas.get(0);
		String[] facturasArray = facturaCompleta.split(",(?![^\\[]*\\])");
		for (int i = 0; i < facturasArray.length; i++) {
			dto.setIdFactura(facturasArray[0].trim());
			dto.setFechaLimpieza(facturasArray[1].trim());
			dto.setNumeroDocumento(facturasArray[2].trim());
			dto.setTipoDocumento(facturasArray[3].trim());
			dto.setNombreCliente(facturasArray[4].trim());
			dto.setEmail(facturasArray[5].trim());
			dto.setCelular(facturasArray[6].trim());
			String productos = facturasArray[7].replace("[", "").replace("]", "");
			dto.setProductos(productos);
			dto.setNombreLimpieza(facturasArray[8].trim());
		}

		return dto;

	}

	 @GetMapping("/todas")
	    public ResponseEntity<List<InvoiceDetailDTO>> listarFacturas() {
	        List<String> facturas = facturaService.listarFacturas();
	        List<InvoiceDetailDTO> listaFacturasDTO = new ArrayList<>();

	        for (String factura : facturas) {
	            String[] facturaArray = factura.split(",(?![^\\[]*\\])");
	            InvoiceDetailDTO dto = new InvoiceDetailDTO();

	            dto.setIdFactura(facturaArray[0]);
	            dto.setFechaLimpieza(facturaArray[1]);
	            dto.setNumeroDocumento(facturaArray[2]);
	            dto.setTipoDocumento(facturaArray[3]);
	            dto.setNombreCliente(facturaArray[4]);
	            dto.setEmail(facturaArray[5]);
	            dto.setCelular(facturaArray[6]);
	            String productosRaw = facturaArray[7];
	            String productosSinCorchetes = productosRaw.replace("[", "").replace("]", "").trim();
	            String[] productosArray = productosSinCorchetes.split(",\\s*");
	            String productosString = String.join(", ", productosArray);

	            dto.setProductos(productosString);
	            dto.setNombreLimpieza(facturaArray[8]);
	            listaFacturasDTO.add(dto);
	        }
	        return ResponseEntity.ok(listaFacturasDTO);
	    }

}