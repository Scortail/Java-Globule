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
    private float oxygenation;
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

    public void initSystem() {
        initCells(TCell.class, 10); // Initialiser 10 TCells
        initCells(BCell.class, 10); // Initialiser 10 BCells
        initCells(RedBloodCell.class, 10); // Initialiser 10 Red Blo
    }

    private void initCells(Class<? extends Cell> cellType, int count) {
        for (int i = 0; i < count; i++) {
            int position = random.nextInt(MAX_POSITION);
            Cell cell;
            if (cellType.equals(TCell.class)) {
                cell = cellFactory.createTCell();
            } else if (cellType.equals(BCell.class)) {
                cell = cellFactory.createBCell();
            } else if (cellType.equals(RedBloodCell.class)) {
                cell = cellFactory.createRedBloodCell();
            } else {
                continue; // Si le type de cellule n'est pas reconnu, continuez la boucle
            }
            cell.setPosition(position);
            sang.add(cell);
        }
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

    public float getOxygenation() {
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
            } else if (entite instanceof Bacteria) {
                countBacteria++;
            } else if (entite instanceof Virus) {
                countViruses++;
            }
        }
        report += "Taux d'oxygenation : " + oxygenation + "\n";
        report += "Ressources disponibles : " + ressources + "\n";
        report += "Nombre de T-Cells: " + countTCells + "\n";
        report += "Nombre de B-Cells: " + countBCells + "\n";
        report += "Nombre de Red Blood Cells: " + countRedBloodCells + "\n";
        report += "Nombre de Bactéries: " + countBacteria + "\n";
        report += "Nombre de Virus: " + countViruses + "\n";
        return report;
    }

    public void generateRandomPathogens() {
        if (random.nextInt(10) == 1) {
            int position = random.nextInt(MAX_POSITION);
            addEntite(new Virus(position, this));
        }

        if (random.nextInt(10) == 1) {
            int position = random.nextInt(MAX_POSITION);
            addEntite(new Bacteria(position, this));
        }
    }

    public void phagocythose() {
        for (Entite entite : sang) {
            if (entite.getEtat().getNom() == "Dead") {
                sang.remove(entite);
                ressources += 10;
            }
        }
    }

    private void executeActions() {
        try {
            turnCounter++;
            oxygenation -= 0.1;
            if (turnCounter % 3 == 0) {
                generateRandomPathogens();
            }
            if (turnCounter % 5 == 0) {
                phagocythose();
            }
            for (Entite entite : sang) {
                entite.action();
            }
            verifierOxygenationEtArreterJeu();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'exécution des actions: " + e.getMessage());
        }
    }

    public void ajouterOxygen(int nb) {
        if (oxygenation + nb > 100) {
            oxygenation = 100;
        } else {
            oxygenation += nb;
        }
    }

    public void retirerOxygen(int nb) {
        if (oxygenation - nb < 0) {
            oxygenation = 0;
        } else {
            oxygenation -= nb;
        }
    }

    private void verifierOxygenationEtArreterJeu() {
        if (oxygenation < 50) {
            stopSystem();
            System.out.println("Vous avez perdu, l'oxygénation est tombée en dessous de 50.");
        }
    }

    public void startSystem() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
        }
        scheduler.scheduleAtFixedRate(this::executeActions, 0, 1, TimeUnit.SECONDS);
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
