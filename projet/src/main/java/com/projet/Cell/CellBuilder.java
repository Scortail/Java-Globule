package com.projet.Cell;

import com.projet.SystemSanguain;

public abstract class CellBuilder {

    protected Cell cell;

    public Cell getCell() {
        return cell;
    }

    public abstract void createNewCell(int position, SystemSanguain system);

    public abstract void addMembrane();

    public abstract void addNucleus();

    public abstract void addCytoplasm();
}
