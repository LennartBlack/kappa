package kappa.control;

import java.security.NoSuchAlgorithmException;

import javafx.util.Duration;
import kappa.model.User;
import kappa.utils.Hash;
import kappa.view.KappaStage;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;

public class Controller {
    private KappaStage stage;
    public Controller(KappaStage stage){
        this.stage = stage;

        // Eventhandler for the sign in button
        this.stage.getSignInPane().getSignInButton().setOnAction(e -> {
            String usernameInput = this.stage.getSignInPane().getUsernameInput();
            String passwordInput = this.stage.getSignInPane().getPasswordInput();
            try{
                String hashedPassword = Hash.hash(passwordInput);
                //Validate Credintials
                if(validateUser(usernameInput, hashedPassword)){
                    clearInputs();
                    if(this.stage.getSignInPane().isLogininFaildMessageVisible()){
                        System.out.println("Removing login failed message");
                        this.stage.getSignInPane().removeLoginFailedMessage();
                    }
                    System.out.println("Showing home scene");
                    this.stage.showHomeScene();
                }else if(validateAdmin(usernameInput, hashedPassword)){
                    clearInputs();
                    this.stage.showAdminHomeScene();
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
        
        // Eventhandler for the userField to jump to the passwordField
        this.stage.getSignInPane().getUserField().setOnAction(e -> {
            this.stage.getSignInPane().getPasswordField().requestFocus();
        });
        
        //Eventhandler for the passwordField to trigger the sign in button
        this.stage.getSignInPane().getPasswordField().setOnAction(e -> {
            this.stage.getSignInPane().getSignInButton().fire();
        });
    
        // Eventhandler for the help button to show the help scene
        this.stage.getMenuPane().getHelpButton().setOnAction(e -> {
            this.stage.showHelpScene();
        });

        // Eventhandler for the watchlist button to show the watchlist scene
        this.stage.getMenuPane().getWatchlistButton().setOnAction(e -> {
            this.stage.showWatchlistScene();
        });

        // Eventhandler for the topWorkloadCable button to topWorkloadCable the home scene
        this.stage.getMenuPane().getTopWorkloadCableButton().setOnAction(e -> {
            this.stage.showCableWithTopWorkloudScene();
        });

        // Eventhandler for the newWindowSearch button to show the newWindowSearch scene
        this.stage.getMenuPane().getNewWindowSearchButton().setOnAction(e -> {
            //TODO: Implement new window search
        });

        // Eventhandler for the searchCable button to show the searchCable scene
        this.stage.getMenuPane().getSearchCableButton().setOnAction(e -> {
            this.stage.showSearchCableScene();
        });
    
        // Eventhandler for the logOff button to show the sign in scene
        this.stage.getMenuPane().getLogOffButton().setOnAction(e -> {
            this.stage.showSignInSceneAfterLogout();
        });
    }

    /**
     * This method shakes a node to indicate that the input was invalid
     * @param node
     */
    private void shakeNode(Node node) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(50), node);
        transition.setFromX(0);
        transition.setByX(10);
        transition.setCycleCount(4);
        transition.setAutoReverse(true);
        transition.play();
    }

    /**
     * This method clears the input fields userField and passwordField after a failed login attempt
     */
    private void clearInputs() {
        this.stage.getSignInPane().getUserField().clear();
        this.stage.getSignInPane().getPasswordField().clear();
    }

    /**
     * This method validates the user input with the admin credentials
     * @param usernameInput
     * @param passwordInput
     * @return true if the input is valid, false otherwise
     */
    private boolean validateAdmin(String usernameInput, String passwordInput) {
        return usernameInput.equals(User.getUsername()) && passwordInput.equals(User.getPasswort());
    }

    /**
     * This method validates the user input with the user credentials
     * @param username which will be compared with the username
     * @param Password which will be compared with the hashed password
     * @return true if the input is valid, false otherwise
     */
    private boolean validateUser(String username, String hashedPasswordInput){
        return username.equals(User.getUsername()) && hashedPasswordInput.equals(User.getPasswort());
    }
}
