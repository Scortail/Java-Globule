package com.projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.projet.Cell.Cell;
import com.projet.Cell.ConcreteCellFactory;
import com.projet.RedCell.RedBloodCell;
import com.projet.WhiteCell.BCell;
import com.projet.WhiteCell.TCell;
import com.projet.pathogene.Bacteria;
import com.projet.pathogene.Virus;

public class SystemSanguain {

    private ArrayList<Entite> sang = new ArrayList<Entite>();
    private ConcreteCellFactory cellFactory = new ConcreteCellFactory(this);
    private int oxygenation;
    private int ressources;
    private static final int MAX_POSITION = 100;
    private Random random = new Random();
    private ScheduledExecutorService scheduler;
    private int turnCounter = 0;

    public SystemSanguain() {
        oxygenation = 100;
        ressources = 100;
        scheduler = Executors.newScheduledThreadPool(1);
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

    public ArrayList<Entite> getSang() {
        return sang;
    }

    public void setSang(ArrayList<Entite> sang) {
        this.sang = sang;
    }

    public static int getMaxPosition() {
        return MAX_POSITION;
    }

    public ArrayList<Entite> findAllEntite(int position) {
        ArrayList<Entite> entites = new ArrayList<Entite>();
        for (Entite entite : sang) {
            if (entite.getPosition() == position) {
                entites.add(entite);
            }
        }
        return entites;
    }

    public void addEntite(Entite entite) {
        this.sang.add(entite);
    }

    public String makeReport() {
        String report = "";
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

        report += "Nombre de T-Cells: " + countTCells + "\n";
        report += "Nombre de B-Cells: " + countBCells + "\n";
        report += "Nombre de Red Blood Cells: " + countRedBloodCells + "\n";
        report += "Nombre de Bactéries: " + countBacteria + "\n";
        report += "Nombre de Virus: " + countViruses + "\n";
        return report;
    }

    public void generateRandomPathogens() {
        int numberOfPathogens = random.nextInt(10) + 1;

        for (int i = 0; i < numberOfPathogens; i++) {
            int position = random.nextInt(MAX_POSITION);
            if (random.nextBoolean()) {
                addEntite(new Virus(position, this));
            } else {
                addEntite(new Bacteria(position, this));
            }
        }
    }

    private void executeActions() {
        System.out.println("action");
        try {
            turnCounter++;
            if (turnCounter % 3 == 0) {
                generateRandomPathogens();
            }
            for (Entite entite : sang) {
                entite.action();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'exécution des actions: " + e.getMessage());
        }
    }

    public void startSystem() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
        }
        scheduler.scheduleAtFixedRate(this::executeActions, 0, 5, TimeUnit.SECONDS);
    }

    public void stopSystem() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
