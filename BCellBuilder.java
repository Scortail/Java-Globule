
public class BCellBuilder extends CellBuilder {

    @Override
    public void createNewCell() {
        cell = new BCell();
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