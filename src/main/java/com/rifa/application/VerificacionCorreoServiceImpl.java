package com.rifa.application;

import com.rifa.adapters.out.external.CorreoService;
import com.rifa.domain.model.VerificacionCorreo;
import com.rifa.domain.ports.in.VerificacionCorreoService;
import com.rifa.domain.ports.out.VerificacionCorreoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class VerificacionCorreoServiceImpl implements VerificacionCorreoService {

    private final VerificacionCorreoRepository verificacionRepo;
    private final CorreoService correoService;

    public VerificacionCorreoServiceImpl(
    	    VerificacionCorreoRepository verificacionRepo,
    	    CorreoService correoService
    	) {
    	    this.verificacionRepo = verificacionRepo;
    	    this.correoService = correoService;
    	}
    @Override
    public void enviarCodigoOTP(String correo) {
    	// Invalidar otros códigos OTP activos
        List<VerificacionCorreo> activos = verificacionRepo.buscarTodosActivosPorCorreo(correo);
        for (VerificacionCorreo verificacion : activos) {
            verificacion.setUsado(true);
            verificacionRepo.guardar(verificacion);
        }    	
    	// Crear nuevo OTP
    	String otp = generarCodigoOTP();
        LocalDateTime ahora = LocalDateTime.now();
        VerificacionCorreo verificacion = new VerificacionCorreo(
                null, correo, otp,
                ahora,
                ahora.plusMinutes(5),
                false
        );
        verificacionRepo.guardar(verificacion);
        
        System.out.println("🔐 OTP para " + correo + ": " + otp); // Simulación del envío de correo
        correoService.enviarCorreo(
        	    correo,
        	    "🔐 Código de verificación",
        	    "Tu código OTP es: " + otp + "\nEste código expira en 5 minutos."
        	);

    }

    @Override
    public boolean verificarCodigo(String correo, String codigo) {
        return verificacionRepo.buscarPorCorreoYCodigo(correo, codigo)
                .filter(v -> !v.isUsado())
                .filter(v -> v.getExpiraEn().isAfter(LocalDateTime.now()))
                .map(v -> {
                    v.setUsado(true);
                    verificacionRepo.guardar(v);
                    return true;
                })
                .orElse(false);
    }

    private String generarCodigoOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
