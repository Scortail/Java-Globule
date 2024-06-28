package com.projet;

public abstract class Entite {
    protected int position;
    protected final int MAX_POSITION = 100;
    protected SystemSanguain system;
    protected StateEntite etat;

    public StateEntite getEtat() {
        return etat;
    }

    public void setEtat(StateEntite etat) {
        this.etat = etat;
    }

    public abstract void action();

    public abstract String getNom();

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SystemSanguain getSystem() {
        return system;
    }

    public void setSystem(SystemSanguain system) {
        this.system = system;
    }

    public abstract void updatePosition();

    public int getMAX_POSITION() {
        return MAX_POSITION;
    }

}
