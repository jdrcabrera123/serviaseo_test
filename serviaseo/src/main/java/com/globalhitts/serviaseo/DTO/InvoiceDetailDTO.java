package com.globalhitts.serviaseo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDTO {
	private String idFactura;
	private String fechaLimpieza;
	private String numeroDocumento;
	private String tipoDocumento;
	private String nombreCliente;
	private String email;
	private String celular;
	private String productos;
	private String nombreLimpieza;

}
