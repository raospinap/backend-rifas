package com.rifa.domain.ports.in;

import java.util.List;

public interface RankingService {
    List<Object[]> obtenerTopUsuariosConMasPremios(int limite);
    
    List<Object[]> obtenerRankingPorRifa(Long rifaId);

}
