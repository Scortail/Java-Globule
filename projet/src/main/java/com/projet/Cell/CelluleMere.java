package com.projet.Cell;

import com.projet.SystemSanguain;

public class CelluleMere {

    private CellBuilder cellBuilder;

    public void setCellBuilder(CellBuilder cb) {
        cellBuilder = cb;
    }

    public Cell getCell() {
        return cellBuilder.getCell();
    }

    public void buildCell(int position, SystemSanguain system) {
        cellBuilder.createNewCell(position, system);
        cellBuilder.addCytoplasm();
        cellBuilder.addNucleus();
        cellBuilder.addMembrane();
    }
}
