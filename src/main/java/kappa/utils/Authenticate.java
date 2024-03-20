package kappa.utils;

public class Authenticate {

    public static boolean validateCredentials(String username, String password){
        return username.equals("admin") && password.equals("geheim");
    }
}
