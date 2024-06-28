package com.projet.pathogene;

import com.projet.Entite;
import com.projet.StateEntite;

public class NormalStateBacteria extends StateEntite {

    private Bacteria bacterie;

    public NormalStateBacteria(Bacteria bacterie) {
        this.bacterie = bacterie;
    }

    @Override
    public void action() {
        for (Entite entite : bacterie.getSystem().findAllEntite(bacterie.getPosition())) {
            if ((entite.getNom() == "RedCell" || entite.getNom() == "BCell")
                    && entite.getEtat().getNom() != "Dead") {
                bacterie.kill(entite);
                return;
            }
        }
    }

    @Override
    public String getNom() {
        return "Alive";
    }

}
