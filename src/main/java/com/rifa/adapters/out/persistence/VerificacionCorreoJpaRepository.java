package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.VerificacionCorreo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface VerificacionCorreoJpaRepository extends JpaRepository<VerificacionCorreo, Long> {

    Optional<VerificacionCorreo> findByCorreoAndOtp(String correo, String otp);
    
    List<VerificacionCorreo> findByCorreoAndUsadoFalse(String correo);

}
