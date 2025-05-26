package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoJpaRepository extends JpaRepository<Evento, Long> {
    List<Evento> findTop10ByOrderByFechaDesc();
}
