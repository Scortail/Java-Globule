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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.projet.RedCell.RedBloodCell;
import com.projet.WhiteCell.BCell;
import com.projet.WhiteCell.TCell;
import com.projet.pathogene.Bacteria;
import com.projet.pathogene.Virus;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class App extends Application {

    private SystemSanguain system;
    private Text statsContent; // Pour mettre à jour les statistiques dynamiquement

    @Override
    public void start(Stage stage) {
        system = new SystemSanguain();
        system.startSystem();

        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        setupGrid(grid);

        HBox buttonBox = setupButtons();
        VBox statsBox = setupStatsPanel();

        root.setBottom(buttonBox);
        root.setCenter(grid);
        root.setRight(statsBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Système Sanguin Interactif");
        stage.setScene(scene);
        stage.show();

        // Timeline pour la mise à jour régulière
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            updateGrid(grid);
            updateStats();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private HBox setupButtons() {
        HBox buttonBox = new HBox(10);
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
        VBox statsBox = new VBox(10);
        Text statsTitle = new Text("Statistiques du Système:");
        statsContent = new Text(); // Texte pour afficher les statistiques mises à jour
        statsBox.getChildren().addAll(statsTitle, statsContent);
        return statsBox;
    }

    private void setupGrid(GridPane grid) {
        grid.setGridLinesVisible(true); // Pour visualiser la grille si nécessaire
        for (int i = 0; i <= SystemSanguain.getMaxPosition(); i++) {
            Circle circle = new Circle(5, Color.TRANSPARENT);
            grid.add(circle, i, 0); // Ajoute un cercle pour chaque position possible
        }
    }

    private void updateGrid(GridPane grid) {
        grid.getChildren().clear();
        setupGrid(grid);
        for (Entite entite : system.getSang()) {
            Circle circle = new Circle(5, getColorForEntite(entite));
            GridPane.setConstraints(circle, entite.getPosition(), 0);
            grid.getChildren().add(circle);
        }
    }

    private void updateStats() {
        String report = system.makeReport(); // Méthode hypothétique retournant une string
        statsContent.setText(report); // Mise à jour du texte des statistiques
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
        return Color.TRANSPARENT; // Par défaut
    }

    public static void main(String[] args) {
        launch(args);
    }
}
