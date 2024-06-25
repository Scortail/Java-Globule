import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SystemSanguain {

    private ArrayList<Entite> sang = new ArrayList<Entite>();
    private ConcreteCellFactory cellFactory = new ConcreteCellFactory();
    private int oxygenation;
    private int ressources;

    public SystemSanguain() {
        oxygenation = 100;
        ressources = 100;
    }

    public void afficherSystem() {
        tri_selection();
        StringBuilder affichage = new StringBuilder();
        for (int index = 0; index <= 100; index++) {
            affichage.append(index + " ");
            for (Entite entite : sang) {
                if (index == entite.position) {
                    affichage.append(entite);
                }
            }
            affichage.append("\n");
        }
        System.out.println(affichage);
    }

    public int getOxygenation() {
        return oxygenation;
    }

    public void setOxygenation(int oxygenation) {
        this.oxygenation = oxygenation;
    }

    public int getRessources() {
        return ressources;
    }

    public void setRessources(int ressources) {
        this.ressources = ressources;
    }

    public void tri_selection() {
        Collections.sort(sang, Comparator.comparingInt(e -> e.position));
    }

    public boolean depenser(int prix) {
        if (ressources - prix >= 0) {
            ressources -= prix;
            return true;
        }
        System.out.println("Pas assez de ressources");
        return false;
    }

    public void acheterCellule(String type) {
        Cell cell = null;
        switch (type) {
            case "TCell":
                if (depenser(20)) {
                    cell = cellFactory.createTCell();
                    break;
                }
            case "BCell":
                if (depenser(10)) {
                    cell = cellFactory.createBCell();
                    break;
                }
            case "RedBloodCell":
                if (depenser(5)) {
                    cell = cellFactory.createRedBloodCell();
                    break;
                }
            default:
                System.out.println("Type de cellule non reconnu");
                return;
        }
        if (cell != null) {
            addEntite(cell);
        }
    }

    public void addEntite(Entite entite) {
        this.sang.add(entite);
    }

    public void makeReport() {
        int countTCells = 0;
        int countBCells = 0;
        int countRedBloodCells = 0;
        int countBacteria = 0;
        int countViruses = 0;

        for (Entite entite : sang) {
            if (entite instanceof TCell) {
                countTCells++;
            } else if (entite instanceof BCell) {
                countBCells++;
            } else if (entite instanceof RedBloodCell) {
                countRedBloodCells++;
            } else if (entite instanceof Bacteria) { // Assuming you have a Bacteria class
                countBacteria++;
            } else if (entite instanceof Virus) { // Assuming you have a Virus class
                countViruses++;
            }
        }

        System.out.println("Rapport du Système Sanguin:");
        System.out.println("Nombre de T-Cells: " + countTCells);
        System.out.println("Nombre de B-Cells: " + countBCells);
        System.out.println("Nombre de Red Blood Cells: " + countRedBloodCells);
        System.out.println("Nombre de Bactéries: " + countBacteria);
        System.out.println("Nombre de Virus: " + countViruses);
    }
}
