package kappa.utils;

public class Authenticate {

    // Constructor
    private Authenticate() {
    }

    // Methods
    public static boolean validateCredentials(String username, String password){
        return username.equals("admin") && password.equals("geheim");
    }
}
