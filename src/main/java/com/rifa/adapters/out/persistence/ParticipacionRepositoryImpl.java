
package com.rifa.adapters.out.persistence;

import com.rifa.domain.ports.out.ParticipacionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipacionRepositoryImpl implements ParticipacionRepository {

    private final ParticipacionJpaRepository jpa;

    public ParticipacionRepositoryImpl(ParticipacionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public long contarPorRifa(Long rifaId) {
        return jpa.countByRifaId(rifaId);
    }
}
