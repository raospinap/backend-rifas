package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.Rifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RifaJpaRepository extends JpaRepository<Rifa, Long> {

    List<Rifa> findByEstado(String estado);
    List<Rifa> findByEstadoAndFechaFinBefore(Rifa.EstadoRifa estado, LocalDateTime fecha);
    
    @Query("SELECT r FROM Rifa r LEFT JOIN FETCH r.premios WHERE r.estado = :estado AND r.fechaFin < :fecha")
    List<Rifa> findConPremiosByEstadoAndFechaFinBefore(@Param("estado") Rifa.EstadoRifa estado, @Param("fecha") LocalDateTime fecha);

    List<Rifa> findByEstado(Rifa.EstadoRifa estado);

}
