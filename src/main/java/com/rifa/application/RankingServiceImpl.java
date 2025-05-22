package com.rifa.application;

import com.rifa.domain.ports.in.RankingService;
import com.rifa.domain.ports.out.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {

    private final UsuarioRepository usuarioRepository;

    public RankingServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
        

    @Override
    public List<Object[]> obtenerTopUsuariosConMasPremios(int limite) {
        return usuarioRepository.obtenerRankingUsuarios(limite);
    }
    
    @Override
    public List<Object[]> obtenerRankingPorRifa(Long rifaId) {
        return usuarioRepository.obtenerRankingPorRifa(rifaId);
    }

}
