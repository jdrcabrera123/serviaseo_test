package com.globalhitts.serviaseo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="clientes")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name="id_cliente")
    private int idCliente;

    @Column(name = "tipo_documento")
    private int tipoDocumento;

    @Column(name = "numero_documento")
    private int numeroDocumento;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "email")
    private String email;

    @Column(name = "celular")
    private int celular;

    @Column(name = "estado")
    private String estado;
}

