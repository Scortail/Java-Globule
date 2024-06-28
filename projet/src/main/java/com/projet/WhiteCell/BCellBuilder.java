package com.projet.WhiteCell;

import com.projet.SystemSanguain;
import com.projet.Cell.CellBuilder;

public class BCellBuilder extends CellBuilder {

    @Override
    public void createNewCell(int position, SystemSanguain system) {
        cell = new BCell(position, system);
    }

    @Override
    public void addMembrane() {
        cell.addPart("Membrane");
    }

    @Override
    public void addNucleus() {
        // Les globules rouges adultes n'ont pas de noyau
    }

    @Override
    public void addCytoplasm() {
        cell.addPart("Cytoplasme");
    }

}
