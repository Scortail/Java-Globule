
public class BCellBuilder extends CellBuilder {

    @Override
    public void createNewCell(int position) {
        cell = new BCell(position);
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
