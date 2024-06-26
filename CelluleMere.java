public class CelluleMere {

    private CellBuilder cellBuilder;

    public void setCellBuilder(CellBuilder cb) {
        cellBuilder = cb;
    }

    public Cell getCell() {
        return cellBuilder.getCell();
    }

    public void buildCell(int position) {
        cellBuilder.createNewCell(position);
        cellBuilder.addCytoplasm();
        cellBuilder.addNucleus();
        cellBuilder.addMembrane();
    }
}
