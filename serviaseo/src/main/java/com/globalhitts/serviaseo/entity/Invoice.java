package com.globalhitts.serviaseo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="facturas")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id_factura")
    private int idFactura;
    @Column(name = "id_limpieza")
    private int idLimpieza;
    @Column(name="id_cliente",nullable = false)
    @JsonIgnore
    private int idCliente;
    private List<Integer> productos;
    @Transient 
    @JsonProperty("tipoDocumento")
    private int tipoDocumento;

    @Transient 
    @JsonProperty("numeroDocumento") 
    private int numeroDocumento;
}
