package com.rifa.application;

import com.rifa.adapters.out.external.CorreoService;
import com.rifa.adapters.out.messaging.EventoProducer;
import com.rifa.adapters.out.messaging.OtpProducer;
import com.rifa.adapters.out.messaging.dto.EventoMessage;
import com.rifa.adapters.out.messaging.dto.OtpMessage;
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
    private final OtpProducer otpProducer;
    private final EventoProducer eventoProducer;

    public VerificacionCorreoServiceImpl(
    	    VerificacionCorreoRepository verificacionRepo,
    	    CorreoService correoService,
    	    OtpProducer otpProducer,
    	    EventoProducer eventoProducer
    	) {
    	    this.verificacionRepo = verificacionRepo;
    	    this.correoService = correoService;
    	    this.otpProducer = otpProducer;
    	    this.eventoProducer = eventoProducer;
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
        
        System.out.println("🔐 OTP para " + correo + ": " + otp); //
        eventoProducer.enviarEvento(new EventoMessage(
        	    "OTP_SOLICITADO",
        	    "📨 OTP enviado a " + correo,
        	    LocalDateTime.now()
        	));

        otpProducer.enviarOtp(new OtpMessage(correo, otp));

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
