package kappa.control;

import java.security.NoSuchAlgorithmException;

import javafx.stage.Stage;
import javafx.util.Duration;
import kappa.model.Admin;
import kappa.model.Kappa;
import kappa.model.User;
import kappa.utils.Hash;
import kappa.view.KappaStage;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.TextField;

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
                    //Validation failed
                    shakeNode(this.stage.getSignInPane().getUserField());
                    shakeNode(this.stage.getSignInPane().getPasswordField());
                    if(!this.stage.getSignInPane().isLogininFaildMessageVisible()){
                        this.stage.getSignInPane().addLoginFailedMessage();
                    }
                    shakeNode(this.stage.getSignInPane().getLoginFailedMessage());
                    clearInputs();
                }
            }
            catch(NoSuchAlgorithmException ex){
                System.out.println("Hashing failed");
            }      
        });
    }

    private void shakeNode(Node node) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(50), node);
        transition.setFromX(0);
        transition.setByX(10);
        transition.setCycleCount(4);
        transition.setAutoReverse(true);
        transition.play();
    }

    private void clearInputs() {
        this.stage.getSignInPane().getUserField().clear();
        this.stage.getSignInPane().getPasswordField().clear();
    }

    private boolean validateAdmin(String usernameInput, String passwordInput) {
        return usernameInput.equals(User.getUsername()) && passwordInput.equals(User.getPasswort());
    }

    private boolean validateUser(String username, String password){
        return username.equals(User.getUsername()) && password.equals(User.getPasswort());
    }
}
