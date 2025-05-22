package com.rifa.domain.exceptions;

public class ParticipacionDuplicadaException extends RuntimeException {

	public ParticipacionDuplicadaException(Long idUsuario, Long idRifa) {
        super("El usuario " + idUsuario + " ya participó en la rifa " + idRifa);
    }
}
