package com.projet.WhiteCell;

import com.projet.DeadState;
import com.projet.Entite;
import com.projet.SystemSanguain;

public class BCell extends WhiteBloodCell {

    public BCell(int position, SystemSanguain system) {
        this.position = position;
        this.system = system;
        this.position = position;
        etat = new NormalStateBCell(this);
    }

    @Override
    public void action() {
        etat.action();
    }

    @Override
    public void updatePosition() {
        if (position >= MAX_POSITION) {
            position = 0;
        } else {
            position += 1;
        }
    }

    public void kill(Entite entite) {
        entite.setEtat(new DeadState(entite));
    }

    public String getNom() {
        return "BCell";
    }
}