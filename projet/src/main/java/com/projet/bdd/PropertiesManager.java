package com.projet.bdd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class PropertiesManager {

    Properties properties = new Properties();

    private String url;
    private SecretKey key;
    private String user;
    private String password;
    private Chiffrement_AES chiffrement = new Chiffrement_AES();

    public PropertiesManager() {
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du fichier properties.");
        }

        try {
            byte[] decodedKey = Base64.getDecoder().decode(properties.getProperty("db.key"));
            this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            this.url = chiffrement.decrypt(properties.getProperty("db.url"), key);
            this.user = chiffrement.decrypt(properties.getProperty("db.user"), key);
            this.password = chiffrement.decrypt(properties.getProperty("db.password"), key);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createProperties(String path) {

        String url = "jdbc:mysql://localhost:3306/cells";
        String username = "root";
        String password = "";

        try {
            SecretKey key = chiffrement.generateAESKey();

            String encryptedUrl = chiffrement.encrypt(url, key);
            String encryptedUsername = chiffrement.encrypt(username, key);
            String encryptedPassword = chiffrement.encrypt(password, key);

            String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());

            properties.setProperty("db.key", encodedKey);
            properties.setProperty("db.url", encryptedUrl);
            properties.setProperty("db.user", encryptedUsername);
            properties.setProperty("db.password", encryptedPassword);

            try (FileOutputStream fos = new FileOutputStream(path)) {
                properties.store(fos, null);
                System.out.println("Fichier properties créé avec succès à l'emplacement : " + path);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de la création du fichier properties.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        PropertiesManager propertiesManager = new PropertiesManager();

        String path = "database.properties";

        propertiesManager.createProperties(path);
    }

}
