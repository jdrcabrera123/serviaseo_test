package com.globalhitts.serviaseo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.globalhitts.serviaseo.entity.Client;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Integer> {

	
	@Query(value ="SELECT * FROM clientes c WHERE c.tipo_documento = :tipoDocumento AND c.numero_documento = :numeroDocumento", nativeQuery= true)
	Optional<Client> findClienteByTipoDocumentoAndNumeroDocumento(@Param("tipoDocumento") int tipoDocumento,
																  @Param("numeroDocumento") int numeroDocumento);

	Optional<Client> findByIdCliente(int idCliente);

	@Query(value = "CALL verificar_cliente(:tipoDocumento, :numeroDocumento)", nativeQuery = true)
	String verificarCliente(@Param("tipoDocumento") int tipoDocumento, @Param("numeroDocumento") int numeroDocumento);

	@Query(value = "CALL registrar_cliente(:tipoDocumento, :numeroDocumento, :nombreCliente, :email, :celular, :estado)", nativeQuery = true)
	void registrarCliente(@Param("tipoDocumento") int tipoDocumento, @Param("numeroDocumento") int numeroDocumento,
			@Param("nombreCliente") String nombreCliente, @Param("email") String email, @Param("celular") int celular,
			@Param("estado") String estado);
}
