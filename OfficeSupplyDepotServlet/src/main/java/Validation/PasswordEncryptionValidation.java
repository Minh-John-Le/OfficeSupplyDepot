package Validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionValidation {
    
    /**
     * Hashes a password string using SHA-256 encryption.
     * @param password the password to hash
     * @return the hashed password as a string
     */
    public static String hashPassword(String password) {
        try {
            // Get an instance of the SHA-256 message digest algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Update the digest with the password bytes
            digest.update(password.getBytes());
            
            // Get the hashed bytes and convert to a hex string
            byte[] hashedBytes = digest.digest();
            String hashedPassword = bytesToHexString(hashedBytes);
            
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception as desired
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Verifies a password against a stored hash.
     * @param password the password to verify
     * @param hashedPassword the stored hashed password
     * @return true if the password is valid, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        // Hash the password using the same algorithm and salt as the stored hash
        String newHashedPassword = hashPassword(password);
        
        // Compare the two hashes to see if they match
        return newHashedPassword.equals(hashedPassword);
    }
    
    /**
     * Converts an array of bytes to a hex string.
     * @param bytes the bytes to convert
     * @return the hex string representation of the bytes
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
