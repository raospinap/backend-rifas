package com.rifa.adapters.in.rest;

import com.rifa.adapters.out.messaging.ParticipacionProducer;
import com.rifa.adapters.out.messaging.dto.ParticipacionMessage;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionController {

    private final ParticipacionProducer participacionProducer;

    public ParticipacionController(ParticipacionProducer participacionProducer) {
        this.participacionProducer = participacionProducer;
    }

    @PostMapping
    public ResponseEntity<?> participar(
            @RequestParam @NotNull Long idUsuario,
            @RequestParam @NotNull Long idRifa) {

        ParticipacionMessage mensaje = new ParticipacionMessage(
                idUsuario,
                idRifa,
                LocalDateTime.now()
        );

        participacionProducer.enviarParticipacion(mensaje);

        return ResponseEntity.ok("Participación enviada exitosamente.");
    }
}
