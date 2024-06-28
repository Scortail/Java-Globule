package com.projet;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import com.projet.RedCell.RedBloodCell;
import com.projet.WhiteCell.BCell;
import com.projet.WhiteCell.TCell;
import com.projet.pathogene.Bacteria;
import com.projet.pathogene.Virus;

/**
 * JavaFX App
 */
public class App extends Application {

    private SystemSanguain system;

    @Override
    public void start(Stage stage) {
        // Initialisation du système
        system = new SystemSanguain();
        system.startSystem();

        // Layout principal
        BorderPane root = new BorderPane();

        // Grille pour les entités
        GridPane grid = new GridPane();
        setupGrid(grid);

        // Configuration des boutons
        HBox buttonBox = setupButtons();

        // Panneau pour les statistiques
        VBox statsBox = setupStatsPanel();

        // Assemblage de la scène
        root.setBottom(buttonBox);
        root.setCenter(grid);
        root.setRight(statsBox);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Système Sanguin Interactif");
        stage.setScene(scene);
        stage.show();

        // Mise à jour régulière de la grille
        updateGrid(grid);
    }

    private HBox setupButtons() {
        HBox buttonBox = new HBox(10); // Espacement entre les boutons
        Button btnAddTCell = new Button("Add T-Cell");
        Button btnAddBCell = new Button("Add B-Cell");
        Button btnAddRedCell = new Button("Add Red Cell");
        btnAddTCell.setOnAction(e -> system.acheterCellule("TCell"));
        btnAddBCell.setOnAction(e -> system.acheterCellule("BCell"));
        btnAddRedCell.setOnAction(e -> system.acheterCellule("RedBloodCell"));
        buttonBox.getChildren().addAll(btnAddTCell, btnAddBCell, btnAddRedCell);
        return buttonBox;
    }

    private VBox setupStatsPanel() {
        VBox statsBox = new VBox(5); // Espacement entre les éléments de stats
        Text statsTitle = new Text("Statistiques du Système:");
        Text statsContent = new Text(system.makeReport()); // Ajoutez ici les statistiques mises à jour
        statsBox.getChildren().addAll(statsTitle, statsContent);
        return statsBox;
    }

    private void setupGrid(GridPane grid) {
        for (int i = 0; i <= SystemSanguain.getMaxPosition(); i++) {
            Circle circle = new Circle(5, Color.TRANSPARENT); // Utilisez TRANSPARENT pour voir les grilles
            grid.add(circle, i, 0); // Une seule ligne pour simplifier
        }
    }

    private void updateGrid(GridPane grid) {
        grid.getChildren().clear(); // Nettoyez le grid pour éviter les doublons
        setupGrid(grid); // Réinitialisez les emplacements avec des cercles transparents
        for (Entite entite : system.getSang()) {
            Circle circle = new Circle(5, getColorForEntite(entite));
            GridPane.setConstraints(circle, entite.getPosition(), 0);
            grid.getChildren().add(circle);
        }
    }

    private Color getColorForEntite(Entite entite) {
        if (entite instanceof TCell) {
            return Color.WHITE;
        } else if (entite instanceof BCell) {
            return Color.GREY;
        } else if (entite instanceof RedBloodCell) {
            return Color.RED;
        } else if (entite instanceof Virus) {
            return Color.BLACK;
        } else if (entite instanceof Bacteria) {
            return Color.BROWN;
        }
        return Color.TRANSPARENT; // Default, pour éviter d'obscurcir le grid
    }

    public static void main(String[] args) {
        launch(args);

        // SystemSanguain system = new SystemSanguain();

        // // Démarrage du système pour exécuter les actions périodiques
        // system.startSystem();

        // // Création et lancement de l'interface utilisateur
        // UserInterface ui = new UserInterface(system);
        // ui.displayMenu();
    }

}