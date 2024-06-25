public class ConcreteCellFactory implements CellFactory {
    private CelluleMere celluleMere;
    protected final int MAX_POSITION = 100;

    public ConcreteCellFactory() {
        this.celluleMere = new CelluleMere();
    }

    @Override
    public TCell createTCell() {
        celluleMere.setCellBuilder(new TCellBuilder());
        celluleMere.buildCell(MAX_POSITION / 2);
        return (TCell) celluleMere.getCell();
    }

    @Override
    public BCell createBCell() {
        celluleMere.setCellBuilder(new BCellBuilder());
        celluleMere.buildCell(MAX_POSITION / 2);
        return (BCell) celluleMere.getCell();
    }

    @Override
    public RedBloodCell createRedBloodCell() {
        celluleMere.setCellBuilder(new RedBloodCellBuilder());
        celluleMere.buildCell(0);
        return (RedBloodCell) celluleMere.getCell();
    }
}