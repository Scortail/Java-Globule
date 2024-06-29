package com.projet.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projet.Entite;
import com.projet.SystemSanguain;
import com.projet.RedCell.RedBloodCell;
import com.projet.pathogene.Bacteria;
import com.projet.pathogene.Virus;
import com.projet.WhiteCell.TCell;
import com.projet.WhiteCell.BCell;

public class DatabaseConnection {

    public static ArrayList<Entite> getBDD(SystemSanguain system) {
        Connection connection = getConnection();
        ArrayList<String> tables = new ArrayList<>();
        tables.add("virus");
        tables.add("bacteria");
        tables.add("tcell");
        tables.add("bcell");
        tables.add("redbloodcell");
        ArrayList<Entite> res = new ArrayList<Entite>();
        for (String table : tables) {
            String query = "SELECT * FROM " + table;
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int position = resultSet.getInt("position");
                    String etat = resultSet.getString("etat");

                    switch (table) {
                        case "bacteria":
                            if (etat == "dead") {
                                res.add(new Bacteria(position, system, etat));
                            } else {
                                res.add(new Bacteria(position, system));
                            }
                            break;
                        case "virus":
                            if (etat == "dead") {
                                res.add(new Virus(position, system, etat));
                            } else {
                                res.add(new Virus(position, system));
                            }
                            break;
                        case "tcell":
                            if (etat == "dead") {
                                res.add(new TCell(position, system, etat));
                            } else {
                                res.add(new TCell(position, system));
                            }
                            break;
                        case "bcell":
                            if (etat == "dead") {
                                res.add(new BCell(position, system, etat));
                            } else {
                                res.add(new BCell(position, system));
                            }
                            break;
                        case "redbloodcell":
                            if (etat == "dead") {
                                res.add(new RedBloodCell(position, system, etat));
                            } else {
                                res.add(new RedBloodCell(position, system));
                            }
                            break;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'exécution de la requête.");
                e.printStackTrace();
            }
        }
        return res;
    }

    public static void viderBDD() {
        Connection connection = getConnection();
        String[] tables = { "bacteria", "virus", "tcell", "bcell", "redbloodcell" };
        for (String table : tables) {
            String query = "DELETE FROM " + table;

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Nombre d'entrées supprimées de " + table + ": " + rowsDeleted);
            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression des données de la table " + table);
                e.printStackTrace();
            }
        }
    }

    public static void sauvegarderEntites(ArrayList<Entite> entites) {
        Connection connection = getConnection();
        for (Entite entite : entites) {
            String type = entite.getClass().getSimpleName().toLowerCase();
            String etat = entite.getEtat().getNom();
            int position = entite.getPosition();

            String query = "INSERT INTO " + type
                    + " (position, etat) VALUES (?, ?) ON DUPLICATE KEY UPDATE etat = VALUES(etat), position = VALUES(position)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, position);
                statement.setString(2, etat);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Entité sauvegardée ou mise à jour : " + type + " à la position " + position
                            + " avec l'état " + etat);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la sauvegarde de l'entité : " + type);
                e.printStackTrace();
            }
        }
    }

    public static void ajouterBDD(Connection connection, String table, int position, String etat) {
        String query = "INSERT INTO " + table + " (position, etat) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, position);
            statement.setString(2, etat);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("element ajouter a " + table);
            }
        } catch (SQLException e) {
            System.out.println("erreur lors de l'insertion a " + table);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        PropertiesManager properties = new PropertiesManager();

        String url = properties.getUrl();
        String user = properties.getUser();
        String password = properties.getPassword();

        // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Pilote chargé avec succès !");
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le pilote JDBC.");
            e.printStackTrace();
            return null;
        }

        // Établir la connexion
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connexion réussie à la base de données !");
                return connection;
            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données.");
            e.printStackTrace();
        }
        return null;
    }

}