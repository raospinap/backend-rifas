package com.rifa.adapters.in.messaging;

import com.rifa.adapters.out.messaging.dto.OtpMessage;
import com.rifa.adapters.out.external.CorreoService;
import com.rifa.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OtpConsumer {

    private final CorreoService correoService;

    public OtpConsumer(CorreoService correoService) {
        this.correoService = correoService;
    }

    @RabbitListener(queues = RabbitMQConfig.OTP_QUEUE)
    public void recibirOtp(OtpMessage mensaje) {
        System.out.println("📩 Enviando OTP a " + mensaje.getCorreo());
        correoService.enviarCorreo(
        	    mensaje.getCorreo(),
        	    "🔐 Código de verificación",
        	    "Tu código OTP es: " + mensaje.getCodigo() + "\nEste código expira en 5 minutos."
        	);

    }
}
