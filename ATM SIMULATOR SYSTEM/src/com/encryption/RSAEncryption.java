package com.encryption;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;
import java.util.Random;

public class RSAEncryption {
    private static final String ALGORITHM = "RSA";
    private static final String SHA_ALGORITHM = "SHA-512";
    private static final int KEY_SIZE = 1024;

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SHA_ALGORITHM);
        return md.digest(input.getBytes());
    }

    public static byte[] encrypt(byte[] plaintext, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext);
    }

    public static byte[] decrypt(byte[] ciphertext, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(ciphertext);
    }

    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = generateKeyPair();

        Random ran = new Random();

        // Encrypt carnumber and pin
        String cardnumber = "" + Math.abs((ran.nextLong() % 90000000L) + 970422000000000L);
        String pinnumber = "" + Math.abs((ran.nextLong() % 900000L) + 100000L);
        ;
        byte[] carnumberHash = hash(cardnumber);
        byte[] pinHash = hash(pinnumber);
        byte[] encryptedCarnumber = encrypt(carnumberHash, keyPair.getPublic());
        byte[] encryptedPin = encrypt(pinHash, keyPair.getPublic());

        // Print encrypted carnumber and pin
        System.out.println("Encrypted CarNumber: " + Base64.getEncoder().encodeToString(encryptedCarnumber));
        System.out.println("Encrypted Pin: " + Base64.getEncoder().encodeToString(encryptedPin));

        // Decrypt carnumber and pin
        byte[] decryptedCarnumberHash = decrypt(encryptedCarnumber, keyPair.getPrivate());
        byte[] decryptedPinHash = decrypt(encryptedPin, keyPair.getPrivate());
        String decryptedCarnumber = new String(decryptedCarnumberHash);
        String decryptedPin = new String(decryptedPinHash);

        // Print decrypted carnumber and pin
        System.out.println("CarNumber: " + cardnumber);
        System.out.println("Decrypted CarNumber: " + decryptedCarnumber);
        System.out.println("Pin: " + pinnumber);
        System.out.println("Decrypted Pin: " + decryptedPin);
    }
}




