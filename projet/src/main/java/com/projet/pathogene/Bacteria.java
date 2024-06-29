package com.projet.pathogene;

import com.projet.DeadState;
import com.projet.Entite;
import com.projet.SystemSanguain;

public class Bacteria extends Entite {

    public Bacteria(int position, SystemSanguain system) {
        this.position = position;
        this.system = system;
        etat = new NormalStateBacteria(this);
    }

    public Bacteria(int position, SystemSanguain system, String dead) {
        this.position = position;
        this.system = system;
        etat = new DeadState(this);
    }

    public String getNom() {
        return "Bacteria";
    }

    public void kill(Entite entite) {
        entite.setEtat(new DeadState(entite));
    }

    @Override
    public void action() {
        etat.action();
    }

    @Override
    public void updatePosition() {

    }

}
