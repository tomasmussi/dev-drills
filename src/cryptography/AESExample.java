package cryptography;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESExample {

    // Method to generate a new AES key
    public static SecretKey generateKey(int n) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    // Method to convert a SecretKey to a base64 encoded string
    public static String encodeKey(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Method to convert a base64 encoded string to a SecretKey
    public static SecretKey decodeKey(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // Method to encrypt a plaintext string using a SecretKey
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt a ciphertext string using a SecretKey
    public static String decrypt(String ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Generate a new AES key
            SecretKey key = generateKey(128);
            String encodedKey = encodeKey(key);
            System.out.println("Generated Key (Base64): " + encodedKey);

            // Encrypt a sample plaintext
            String plaintext = "Hello, World!";
            String ciphertext = encrypt(plaintext, key);
            System.out.println("Encrypted Text: " + ciphertext);

            // Decrypt the ciphertext
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("Decrypted Text: " + decryptedText);

            // Demonstrate encoding and decoding the key
            SecretKey decodedKey = decodeKey(encodedKey);
            String ciphertext2 = encrypt(plaintext, decodedKey);
            System.out.println("Encrypted Text with Decoded Key: " + ciphertext2);
            String decryptedText2 = decrypt(ciphertext2, decodedKey);
            System.out.println("Decrypted Text with Decoded Key: " + decryptedText2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
