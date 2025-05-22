package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);
    
    @Query("""
    	    SELECT u.correo, COUNT(p.id) AS totalPremios
    	    FROM Premio p
    	    JOIN p.ganador u
    	    GROUP BY u.correo
    	    ORDER BY totalPremios DESC
    	""")
    	List<Object[]> obtenerRankingUsuarios(Pageable pageable);



}
