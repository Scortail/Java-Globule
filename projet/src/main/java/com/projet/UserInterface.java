package com.projet;

import java.util.Scanner;

public class UserInterface {
    private SystemSanguain system;
    private Scanner scanner;

    public UserInterface(SystemSanguain system) {
        this.system = system;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Bienvenue dans le système sanguin interactif!");
        System.out.println("Oxygenation: " + system.getOxygenation() + "  | Ressources : " + system.getRessources());
        System.out.println("1. Ajouter une TCell");
        System.out.println("2. Ajouter une BCell");
        System.out.println("3. Ajouter une RedBloodCell");
        System.out.println("4. Afficher le système");
        System.out.println("5. Générer un rapport");
        System.out.println("6. Quitter");
        System.out.print("Entrez votre choix: ");

        int choice = scanner.nextInt();
        processChoice(choice);
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                system.acheterCellule("TCell");
                break;
            case 2:
                system.acheterCellule("BCell");
                break;
            case 3:
                system.acheterCellule("RedBloodCell");
                break;
            case 4:
                system.afficherSystem();
                break;
            case 5:
                system.makeReport();
                break;
            case 6:
                System.out.println("Merci d'avoir utilisé le système sanguin interactif!");
                System.exit(0);
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
        displayMenu();
    }
}
