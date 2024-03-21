package kappa.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String hash(String value) throws NoSuchAlgorithmException{
        String algorithm = "SHA-256";
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashedInput = digest.digest(value.getBytes());
        String hashedInputString = BuildStringFromBytes.buildStringFromBytes(hashedInput);
        return hashedInputString;
    }
}
