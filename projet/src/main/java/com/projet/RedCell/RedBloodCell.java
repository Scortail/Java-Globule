package com.projet.RedCell;

import com.projet.SystemSanguain;
import com.projet.Cell.Cell;

public class RedBloodCell extends Cell {

    private boolean oxygen;

    public RedBloodCell(int position, SystemSanguain system) {
        this.position = position;
        this.system = system;
        this.position = position;
        etat = new NormalRedBloodCellState(this);
    }

    public boolean isOxygen() {
        return oxygen;
    }

    public void setOxygen(boolean oxygen) {
        this.oxygen = oxygen;
    }

    public void takeOxygen() {
        if (position == 0) {
            oxygen = true;
        }
    }

    // Ajouter l'oxygen au system
    public void giveOxygen() {
        if (position == MAX_POSITION) {
            oxygen = false;
        }
    }

    @Override
    public void updatePosition() {
        if (position >= MAX_POSITION) {
            position = 0;
        } else {
            position += 1;
        }
    }

    public String getNom() {
        return "RedCell";
    }

    @Override
    public void action() {
        etat.action();
    }

}
