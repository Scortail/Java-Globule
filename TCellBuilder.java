
public class TCellBuilder extends CellBuilder {

    @Override
    public void createNewCell() {
        cell = new TCell();
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