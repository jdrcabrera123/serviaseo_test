package com.globalhitts.serviaseo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globalhitts.serviaseo.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    
    
    @Query(value = "CALL crear_factura(:idCliente, :idLimpieza, :productos)", nativeQuery = true)
    void crearFactura(@Param("idCliente") int idCliente,
                      @Param("idLimpieza") int idLimpieza,
                      @Param("productos") String productos);
    
    @Query(value = "SELECT f.id_factura, f.fecha_limpieza, c.numero_documento, c.tipo_documento, c.nombre_cliente, "
    		+ "c.email, c.celular, "
    		+ "CONCAT('[', GROUP_CONCAT(DISTINCT p.nombre_producto SEPARATOR ', '), ']') AS productos, "
    		+ "l.nombre_limpieza "
    		+ "FROM facturas f "
    		+ "INNER JOIN clientes c ON f.id_cliente = c.id_cliente "
    		+ "INNER JOIN factura_producto fp ON f.id_factura = fp.id_factura "
    		+ "INNER JOIN productos p ON fp.id_producto = p.id_producto "
    		+ "INNER JOIN limpiezas l ON f.id_limpieza = l.id_limpieza "
    		+ "WHERE f.id_factura = :idFactura "
    		+ "GROUP BY f.id_factura, c.id_cliente, l.nombre_limpieza;",
            nativeQuery = true)
    List<String> listarFactura(@Param("idFactura") int idFactura);
    
    @Query(value = "SELECT f.id_factura, f.fecha_limpieza, c.numero_documento, c.tipo_documento, c.nombre_cliente, "
    		+ "c.email, c.celular, "
    		+ "CONCAT('[', GROUP_CONCAT(DISTINCT p.nombre_producto SEPARATOR ', '), ']') AS productos, "
    		+ "l.nombre_limpieza "
    		+ "FROM facturas f "
    		+ "INNER JOIN clientes c ON f.id_cliente = c.id_cliente "
    		+ "INNER JOIN factura_producto fp ON f.id_factura = fp.id_factura "
    		+ "INNER JOIN productos p ON fp.id_producto = p.id_producto "
    		+ "INNER JOIN limpiezas l ON f.id_limpieza = l.id_limpieza "
    		+ "GROUP BY f.id_factura, c.id_cliente, l.nombre_limpieza;",
            nativeQuery = true)
    List<String> listarFacturas();
}

