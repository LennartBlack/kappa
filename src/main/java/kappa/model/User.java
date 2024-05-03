package kappa.model;

public class User {

    // Attribute
    private static String username = "user";
    private static String passwort = "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb";

    /**
     * Constructor for the User class
     */
    private User() {
    }

    // Getter
    /**
     * Getter for the username attribute
     * @return
     */
    public static String getUsername() {
        return username;
    }
    /**
     * Getter for the passwort attribute
     * @return
     */
    public static String getPasswort() {
        return passwort;
    }
}
