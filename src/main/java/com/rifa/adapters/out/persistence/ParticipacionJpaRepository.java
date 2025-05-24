package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.Participacion;
import com.rifa.domain.model.Rifa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipacionJpaRepository extends JpaRepository<Participacion, Long> {

	boolean existsByIdUsuarioAndRifa_Id(Long idUsuario, Long idRifa);
	
	List<Participacion> findByRifa(Rifa rifa);
	
	@Query("SELECT DISTINCT p.rifa.id FROM Participacion p WHERE p.idUsuario = :idUsuario")
	List<Long> findIdsRifasPorUsuario(@Param("idUsuario") Long idUsuario);

	long countByRifaId(Long rifaId);

}
