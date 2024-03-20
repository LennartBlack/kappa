package kappa.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public void hash(String value){
        String algorithm = "SHA-256";
        try{
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(value.getBytes());
            String hex = BuildStringFromBytes.buildStringFromBytes(hash);
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }


}
