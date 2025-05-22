package com.rifa.adapters.out.persistence;

import com.rifa.adapters.in.rest.dto.PremioUsuarioDTO;
import com.rifa.domain.model.Premio;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PremioJpaRepository extends JpaRepository<Premio, Long> {

	@Query("""
		    SELECT u.correo, p.nombre
		    FROM Premio p
		    JOIN p.ganador u
		    WHERE p.rifa.id = :rifaId
		""")
	List<Object[]> obtenerGanadoresPorRifa(@Param("rifaId") Long rifaId);


	@Query("""
		    SELECT new com.rifa.adapters.in.rest.dto.PremioUsuarioDTO(
		        p.nombre, 
		        r.tema, 
		        r.fechaFin
		    )
		    FROM Premio p
		    JOIN p.rifa r
		    WHERE p.ganador.id = :idUsuario
		    ORDER BY r.fechaFin DESC
		""")
		List<PremioUsuarioDTO> obtenerPremiosPorUsuario(@Param("idUsuario") Long idUsuario);


	
	@Query("""
		    SELECT u.correo, p.nombre, p.rifa.fechaFin
		    FROM Premio p
		    JOIN p.ganador u
		    ORDER BY p.id DESC
		""")
		List<Object[]> obtenerUltimosPremios(Pageable pageable);

	
}
