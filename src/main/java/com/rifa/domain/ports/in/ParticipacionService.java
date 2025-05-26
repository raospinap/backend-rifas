package com.rifa.domain.ports.in;

import java.time.LocalDateTime;

public interface ParticipacionService {
    void registrarParticipacion(Long idUsuario, Long idRifa, LocalDateTime fechaHora);
}
