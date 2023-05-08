package atm.simulator.system;

import com.encryption.RSAEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.util.Base64;

public class RSAKeyManager {
    private static KeyPair keyPair = loadKeyPair();

    private static final String KEY_FILENAME = "mykeypair.key";

    public static KeyPair loadKeyPair() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(KEY_FILENAME));
            KeyPair keyPair = (KeyPair) inputStream.readObject();
            inputStream.close();
            return keyPair;
        }
        catch (IOException | ClassNotFoundException e) {
            KeyPair keyPair = null;

            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(KEY_FILENAME));
                keyPair = RSAEncryption.generateKeyPair();
                outputStream.writeObject(keyPair);
                outputStream.close();
            } catch (NoSuchAlgorithmException  | IOException ex) {
                throw new RuntimeException(ex);
            }

            return keyPair;
        }
    }

    public static String hash(String plainText) {
        try {
            byte[] plainBytes = plainText.getBytes();
            byte[] encryptedBytes = RSAEncryption.encrypt(plainBytes, keyPair.getPublic());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            // Handle exception
            return null;
        }
    }

    public static boolean verify(String input, String encryptedInput){
        try {
            byte[] decryptedBase64 = Base64.getDecoder().decode(encryptedInput);
            byte[] decryptedRSA = RSAEncryption.decrypt(decryptedBase64, keyPair.getPrivate());

            return new String(decryptedRSA).equals(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}