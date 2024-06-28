package com.projet;

public class DeadState extends StateEntite {

    private Entite entite;

    public DeadState(Entite entite) {
        this.entite = entite;
    }

    @Override
    public void action() {
    }

    public String getNom() {
        return "Dead";
    }
}
