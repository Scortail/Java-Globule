package com.projet.Cell;

import java.util.ArrayList;
import com.projet.Entite;

public abstract class Cell extends Entite {
    private ArrayList<String> parts;

    boolean alive;

    public Cell() {
        this.parts = new ArrayList<>();
        this.position = 0;
        this.alive = true;
    }

    public void addPart(String part) {
        this.parts.add(part);
    }

    public void showParts() {
        System.out.println("Cell consists of: " + String.join(", ", parts));
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract void updatePosition();
}