package com.globalhitts.serviaseo.controller;

import com.globalhitts.serviaseo.DTO.DetalleFacturaDTO;
import com.globalhitts.serviaseo.entity.Cliente;
import com.globalhitts.serviaseo.entity.Factura;
import com.globalhitts.serviaseo.service.ClienteService;
import com.globalhitts.serviaseo.service.EmailService;
import com.globalhitts.serviaseo.service.FacturaService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

	private final ClienteService clienteService;
	private final FacturaService facturaService;
	private final EmailService emailService;

	public FacturaController(ClienteService clienteService, FacturaService facturaService, EmailService emailService) {
		this.clienteService = clienteService;
		this.facturaService = facturaService;
		this.emailService = emailService;
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crearFactura(@RequestBody Factura factura) {
		int tipoDocumento = factura.getTipoDocumento();
		int numeroDocumento = factura.getNumeroDocumento();
		Optional<Cliente> clienteOpt = clienteService.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento,
				numeroDocumento);
		if (clienteOpt.isPresent()) {
			Cliente cliente = clienteOpt.get();
			int idCliente = cliente.getIdCliente();

			String productosJson = new Gson().toJson(factura.getProductos());

			facturaService.crearFactura(idCliente, factura, productosJson);

			String toEmail = cliente.getEmail();
			String subject = "Factura Generada - ID: " + factura.getIdFactura();
			String body = "Se ha generado una factura con éxito.\n\n" + "Detalles de la factura:\n" + "ID Cliente: "
					+ idCliente + "\n" + "ID Limpieza: " + factura.getIdLimpieza() + "\n" + "Productos: "
					+ productosJson;

			emailService.enviarFactura(toEmail, subject, body);

			return new ResponseEntity<>("Factura creada y enviada al cliente.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("El cliente no existe. Por favor, registre al cliente.", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("buscar/{idFactura}")
	public DetalleFacturaDTO obtenerFactura(@PathVariable int idFactura) {
		DetalleFacturaDTO dto = new DetalleFacturaDTO();
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
	    public ResponseEntity<List<DetalleFacturaDTO>> listarFacturas() {
	        List<String> facturas = facturaService.listarFacturas();
	        List<DetalleFacturaDTO> listaFacturasDTO = new ArrayList<>();

	        for (String factura : facturas) {
	            String[] facturaArray = factura.split(",(?![^\\[]*\\])");
	            DetalleFacturaDTO dto = new DetalleFacturaDTO();

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