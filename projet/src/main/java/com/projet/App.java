package com.projet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.projet.RedCell.RedBloodCell;
import com.projet.WhiteCell.BCell;
import com.projet.WhiteCell.TCell;
import com.projet.bdd.DatabaseConnection;
import com.projet.pathogene.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class App extends Application {

    private SystemSanguain system;
    private Text statsContent;

    @Override
    public void start(Stage primaryStage) {
        startMenu(primaryStage);
    }

    private void startMenu(Stage stage) {
        VBox menuBox = new VBox(10);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(20));

        Button newGameButton = new Button("Nouvelle Partie");
        newGameButton.setOnAction(e -> startNewGame(stage));

        Button continueButton = new Button("Continuer");
        continueButton.setOnAction(e -> continueGame(stage));

        Button quitButton = new Button("Quitter");
        quitButton.setOnAction(e -> System.exit(0));

        menuBox.getChildren().addAll(newGameButton, continueButton, quitButton);

        Scene scene = new Scene(menuBox, 400, 300);
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    private void startNewGame(Stage stage) {
        DatabaseConnection.viderBDD();
        system = new SystemSanguain();
        system.initSystem();
        system.startSystem();

        mainGameInterface(stage);
    }

    private void continueGame(Stage stage) {
        system = new SystemSanguain();
        system.setSang(DatabaseConnection.getBDD(system));
        system.startSystem();
        mainGameInterface(stage);
    }

    public void mainGameInterface(Stage stage) {
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 0)); // Espacements autour de la grille
        grid.setVgap(2); // Espacement vertical entre les cellules

        setupGrid(grid);
        setupPositionHeaders(grid);

        HBox buttonBox = setupButtons();
        VBox statsBox = setupStatsPanel(); // Contient maintenant seulement les statistiques
        VBox legendBox = setupLegendBox(); // Nouveau VBox pour la légende

        // Positionnement dans le BorderPane
        root.setBottom(buttonBox);
        root.setCenter(grid);
        BorderPane.setAlignment(buttonBox, Pos.CENTER);
        BorderPane.setAlignment(grid, Pos.CENTER);

        // Création d'un conteneur pour la partie droite
        VBox rightBox = new VBox();
        rightBox.getChildren().addAll(statsBox, legendBox);
        root.setRight(rightBox);
        VBox.setVgrow(legendBox, Priority.ALWAYS); // Assure que la légende reste en bas

        Scene scene = new Scene(root, 1200, 800);
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

    private void setupPositionHeaders(GridPane grid) {
        for (int i = 0; i <= SystemSanguain.getMaxPosition(); i++) {
            Text positionLabel = new Text(Integer.toString(i + 1));
            grid.add(positionLabel, i, 0);
        }
    }

    private VBox setupLegendBox() {
        VBox legendBox = new VBox(5); // Espacement entre les éléments de la légende
        legendBox.setAlignment(Pos.BOTTOM_RIGHT); // Alignement en bas à droite
        legendBox.setPadding(new Insets(10)); // Un peu d'espacement autour des bords de la légende

        // Ajout du titre "Légende"
        Text legendTitle = new Text("Légende");
        legendTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;"); // Style pour le titre

        HBox legend = createLegend(); // Crée la légende des couleurs
        legend.setAlignment(Pos.CENTER); // Centre les éléments de légende dans leur boîte

        // Ajoute le titre et la légende à la boîte
        legendBox.getChildren().addAll(legendTitle, legend);
        return legendBox;
    }

    private HBox createLegend() {
        HBox legend = new HBox(10);
        legend.setAlignment(Pos.CENTER);
        legend.getChildren().addAll(
                createColorLegend("T-Cell", Color.GREEN),
                createColorLegend("B-Cell", Color.YELLOW),
                createColorLegend("Red Blood Cell", Color.RED),
                createColorLegend("Virus", Color.PURPLE),
                createColorLegend("Bacteria", Color.GRAY),
                createColorLegend("Dead", Color.BLACK));
        return legend;
    }

    private HBox setupButtons() {
        HBox buttonBox = new HBox(10);
        Button btnAddTCell = new Button("Add T-Cell");
        Button btnAddBCell = new Button("Add B-Cell");
        Button btnAddRedCell = new Button("Add Red Cell");
        Button btnSave = new Button("Save");
        btnAddTCell.setOnAction(e -> system.acheterCellule("TCell"));
        btnAddBCell.setOnAction(e -> system.acheterCellule("BCell"));
        btnAddRedCell.setOnAction(e -> system.acheterCellule("RedBloodCell"));
        btnSave.setOnAction(e -> DatabaseConnection.sauvegarderEntites(system.getSang()));
        btnSave.setOnAction(e -> DatabaseConnection.sauvegarderEntites(system.getSang()));

        buttonBox.getChildren().addAll(btnAddTCell, btnAddBCell, btnAddRedCell, btnSave);
        return buttonBox;
    }

    private VBox setupStatsPanel() {
        VBox statsBox = new VBox(10);
        Text statsTitle = new Text("Statistiques du Système:");
        statsContent = new Text();
        statsBox.getChildren().addAll(statsTitle, statsContent);
        return statsBox;
    }

    private HBox createColorLegend(String name, Color color) {
        Circle colorIndicator = new Circle(5, color);
        Text labelText = new Text(name);
        HBox hbox = new HBox(5, colorIndicator, labelText);
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }

    private void setupGrid(GridPane grid) {
        for (int i = 0; i <= SystemSanguain.getMaxPosition(); i++) {
            for (int j = 1; j < 2; j++) {
                Circle circle = new Circle(10, Color.TRANSPARENT);
                grid.add(circle, i, j);
            }
        }
    }

    private void updateGrid(GridPane grid) {
        // Efface tous les enfants du grid à l'exception des en-têtes de position
        grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);

        // Dessine les entités à leurs positions actuelles
        for (Entite entite : system.getSang()) {
            Circle circle = new Circle(10, getColorForEntite(entite));
            int position = entite.getPosition();
            // Déterminer à quelle "hauteur" dans la colonne le cercle doit être ajouté
            long countAtPosition = grid.getChildren().stream()
                    .filter(c -> GridPane.getColumnIndex(c) == position && GridPane.getRowIndex(c) > 0)
                    .count();
            GridPane.setConstraints(circle, position, (int) countAtPosition + 1); // Décale les entités sur la même
                                                                                  // colonne
            grid.getChildren().add(circle);
        }
    }

    private void updateStats() {
        String report = system.makeReport(); // Méthode hypothétique retournant une string
        statsContent.setText(report); // Mise à jour du texte des statistiques
    }

    private Color getColorForEntite(Entite entite) {
        if (entite.getEtat().getNom() == "Dead") {
            return Color.BLACK;
        }
        if (entite instanceof TCell) {
            return Color.GREEN;
        } else if (entite instanceof BCell) {
            return Color.YELLOW;
        } else if (entite instanceof RedBloodCell) {
            return Color.RED;
        } else if (entite instanceof Virus) {
            return Color.PURPLE;
        } else if (entite instanceof Bacteria) {
            return Color.GRAY;
        }
        return Color.TRANSPARENT;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
