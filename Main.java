public class Main {

    public static void main(String[] args) {
        CelluleMere cm = new CelluleMere();
        RedBloodCellBuilder rbcb = new RedBloodCellBuilder();
        TCellBuilder wbcb = new TCellBuilder();
        cm.setCellBuilder(rbcb);
        cm.buildCell();
        Cell cell = cm.getCell();
        cell.showParts();
    }

}
