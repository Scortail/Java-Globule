package com.projet.Cell;

import com.projet.WhiteCell.*;
import com.projet.SystemSanguain;
import com.projet.RedCell.*;

public class ConcreteCellFactory implements CellFactory {
    private CelluleMere celluleMere;
    protected final int MAX_POSITION = 100;
    protected SystemSanguain system;

    public ConcreteCellFactory(SystemSanguain system) {
        this.system = system;
        this.celluleMere = new CelluleMere();
    }

    @Override
    public TCell createTCell() {
        celluleMere.setCellBuilder(new TCellBuilder());
        celluleMere.buildCell(MAX_POSITION / 2, system);
        return (TCell) celluleMere.getCell();
    }

    @Override
    public BCell createBCell() {
        celluleMere.setCellBuilder(new BCellBuilder());
        celluleMere.buildCell(MAX_POSITION / 2, system);
        return (BCell) celluleMere.getCell();
    }

    @Override
    public RedBloodCell createRedBloodCell() {
        celluleMere.setCellBuilder(new RedBloodCellBuilder());
        celluleMere.buildCell(0, system);
        return (RedBloodCell) celluleMere.getCell();
    }
}