package com.projet.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        PropertiesManager properties = new PropertiesManager();

        String url = properties.getUrl();
        String user = properties.getUser();
        String password = properties.getPassword();

        return DriverManager.getConnection(url, user, password);
    }

    public static String getBDD(String table) {
        StringBuilder result = new StringBuilder();
        String query = "SELECT * FROM " + table;

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Ajout des noms de colonnes
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                result.append(resultSet.getMetaData().getColumnName(i)).append("\t");
            }
            result.append("\n");

            // Ajout des données
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    result.append(resultSet.getString(i)).append("\t");
                }
                result.append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        }

        return result.toString();
    }

    // Méthode pour ajouter une ligne à une table
    public static void ajouterBDD(String table, int position, String etat) {
        String query = "INSERT INTO " + table + " (position, etat) VALUES (?, ?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, position);
            preparedStatement.setString(2, etat);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Une nouvelle ligne a été insérée avec succès dans " + table);
            } else {
                System.out.println("Aucune ligne n'a été insérée dans " + table);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'insertion des données.");
        }
    }

    public static void main(String[] args) {
        // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Pilote chargé avec succès !");
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le pilote JDBC.");
            e.printStackTrace();
            return;
        }

        // Test de la méthode getBDD
        String tableData = getBDD("tcell");
        System.out.println(tableData);
        // Test des méthodes
        ajouterBDD("tcell", 18, "mort");
        String table2 = getBDD("tcell");
        System.out.println(table2);
    }
}