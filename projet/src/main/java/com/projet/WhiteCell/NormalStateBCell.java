package com.projet.WhiteCell;

import com.projet.Entite;
import com.projet.StateEntite;

public class NormalStateBCell extends StateEntite {

    private BCell bCell;

    public NormalStateBCell(BCell bCell) {
        this.bCell = bCell;
    }

    @Override
    public void action() {
        for (Entite entite : bCell.getSystem().findAllEntite(bCell.getPosition())) {
            if ((entite.getNom() == "RedCell" || entite.getNom() == "BCell")
                    && entite.getEtat().getNom() != "Dead") {
                bCell.kill(entite);
                return;
            }
        }
        bCell.updatePosition();
    }

    @Override
    public String getNom() {
        return "Alive";
    }
}
