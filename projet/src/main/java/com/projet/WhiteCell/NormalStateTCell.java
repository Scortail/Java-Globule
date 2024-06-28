package com.projet.WhiteCell;

import com.projet.Entite;
import com.projet.StateEntite;

public class NormalStateTCell extends StateEntite {
    private TCell tCell;

    public NormalStateTCell(TCell tCell) {
        this.tCell = tCell;
    }

    @Override
    public void action() {
        for (Entite entite : tCell.getSystem().findAllEntite(tCell.getPosition())) {
            if ((entite.getNom() == "Virus" || entite.getNom() == "Bacteria")
                    && entite.getEtat().getNom() != "Dead") {
                tCell.kill(entite);
                return;
            }
        }
        tCell.updatePosition();
    }

    @Override
    public String getNom() {
        return "Alive";
    }
}
