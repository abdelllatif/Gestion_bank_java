package service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.CompteEpargne;

public class calculeTauxTask {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start(CompteEpargne compteEpargne) {
        scheduler.scheduleAtFixedRate(() -> {
            analyseAndAugmentTauxInteret(compteEpargne);
            compteEpargne.calculerInteret();
        }, 0, 30, TimeUnit.SECONDS);
    }

    private void analyseAndAugmentTauxInteret(CompteEpargne compte) {
        if (compte.getsolde() > 10000) {
            compte.setTauxInteret(compte.getTauxInteret() +  0.5);
        }
    }
}