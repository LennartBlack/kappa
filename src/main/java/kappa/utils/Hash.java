package kappa.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    // Constructor
    private Hash() {
    }

    // Methods
    public static String hash(String value) throws NoSuchAlgorithmException{
        String algorithm = "SHA-256";
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashedInput = digest.digest(value.getBytes());
        return BuildStringFromBytes.buildStringFromBytes(hashedInput);
    }
}
