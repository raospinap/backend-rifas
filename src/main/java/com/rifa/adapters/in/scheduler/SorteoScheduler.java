package com.rifa.adapters.in.scheduler;

import com.rifa.application.RifaServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SorteoScheduler {

    private final RifaServiceImpl rifaService;

    public SorteoScheduler(RifaServiceImpl rifaService) {
        this.rifaService = rifaService;
    }

    @Scheduled(fixedRate = 60000) // cada 30 seg
    public void ejecutarSorteoAutomatico() {
        System.out.println("⏰ Verificando rifas expiradas...");
        rifaService.sortearRifasVencidas();
    }
}
