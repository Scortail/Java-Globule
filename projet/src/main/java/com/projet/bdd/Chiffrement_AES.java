package com.projet.bdd;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Chiffrement_AES {

    public SecretKey generateAESKey() throws NoSuchAlgorithmException {
        // Créer une instance de KeyGenerator pour AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // Initialiser le KeyGenerator avec la taille de clé (128, 192 ou 256 bits)
        keyGenerator.init(128);
        // Générer la clé secrète
        return keyGenerator.generateKey();
    }

    public String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
