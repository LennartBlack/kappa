package kappa.control;

import java.security.NoSuchAlgorithmException;

import javafx.stage.Stage;
import kappa.model.Admin;
import kappa.model.Kappa;
import kappa.model.User;
import kappa.utils.Hash;
import kappa.view.KappaStage;

public class Controller {
    private KappaStage stage;
    public Controller(KappaStage stage){
        this.stage = stage;

        this.stage.getSignInPane().getSignInButton().setOnAction(e -> {
            String usernameInput = this.stage.getSignInPane().getUsernameInput();
            String passwordInput = this.stage.getSignInPane().getPasswordInput();
            try{
                String hashedPassword = Hash.hash(passwordInput);
                //Validate Credintials
                if(validateUser(usernameInput, hashedPassword)){
                    System.out.println("Validation success");
                    this.stage.showAdminHomeScene();
                }else if(validateAdmin(usernameInput, hashedPassword)){
                    this.stage.showHomeScene();
                }else{
                    System.out.println("Validation failed");
                }
            }
            catch(NoSuchAlgorithmException ex){
                System.out.println("Hashing failed");
            }      
        });
    }

    private boolean validateAdmin(String usernameInput, String passwordInput) {
        return usernameInput.equals(User.getUsername()) && passwordInput.equals(User.getPasswort());
    }

    private boolean validateUser(String username, String password){
        return username.equals(User.getUsername()) && password.equals(User.getPasswort());
    }
}
