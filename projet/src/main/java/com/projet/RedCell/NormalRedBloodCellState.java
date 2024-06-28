package com.projet.RedCell;

import com.projet.StateEntite;

public class NormalRedBloodCellState extends StateEntite {

    private RedBloodCell redCell;

    public NormalRedBloodCellState(RedBloodCell redCell) {
        this.redCell = redCell;
    }

    @Override
    public void action() {
        if (redCell.getPosition() == 0 && redCell.isOxygen() == false) {
            redCell.takeOxygen();

        } else if (redCell.getPosition() == redCell.getMAX_POSITION() && redCell.isOxygen() == true) {
            redCell.giveOxygen();
        }

        else {
            redCell.updatePosition();
        }
    }

    @Override
    public String getNom() {
        return "Alive";
    }

}
