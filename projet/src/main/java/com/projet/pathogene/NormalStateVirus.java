package com.projet.pathogene;

import com.projet.Entite;
import com.projet.StateEntite;

public class NormalStateVirus extends StateEntite {

    private Virus virus;

    public NormalStateVirus(Virus virus) {
        this.virus = virus;
    }

    @Override
    public void action() {
        for (Entite entite : virus.getSystem().findAllEntite(virus.getPosition())) {
            if ((entite.getNom() == "RedCell" || entite.getNom() == "BCell" || entite.getNom() == "TCell")
                    && entite.getEtat().getNom() != "Dead") {
                virus.kill(entite);
                return;
            }
        }
    }

    @Override
    public String getNom() {
        return "Alive";
    }

}
