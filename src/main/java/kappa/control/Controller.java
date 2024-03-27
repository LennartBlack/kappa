package kappa.control;

import java.security.NoSuchAlgorithmException;

import javafx.util.Duration;
import kappa.Main;
import kappa.MainWithoutVMArgs;
import kappa.model.Cable;
import kappa.model.CableCoreDataDB;
import kappa.model.Kappa;
import kappa.model.PreviousViewedCable;
import kappa.model.User;
import kappa.utils.Hash;
import kappa.view.KappaStage;
import kappa.view.PreviousViewedCablesPane;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Controller {
    private KappaStage stage;
    private PreviousViewedCable previousViewedCables;
    private PreviousViewedCablesPane previousViewedCablesPane;
    private CableCoreDataDB cableCoreDataDB;

    public Controller(KappaStage stage, CableCoreDataDB cableCoreDataDB) {
        this.stage = stage;
        this.cableCoreDataDB = cableCoreDataDB;
        this.previousViewedCables = this.stage.getPreviousViewedCablesPane().getPreviousViewedCables();
        this.previousViewedCablesPane = this.stage.getPreviousViewedCablesPane();

        // Eventhandler for the sign in button
        this.stage.getSignInPane().getSignInButton().setOnAction(e -> {
            String usernameInput = this.stage.getSignInPane().getUsernameInput();
            String passwordInput = this.stage.getSignInPane().getPasswordInput();
            try {
                String hashedPassword = Hash.hash(passwordInput);
                // Validate Credintials
                if (validateUser(usernameInput, hashedPassword)) {
                    clearSignInInputs();
                    if (this.stage.getSignInPane().isLogininFaildMessageVisible()) {
                        System.out.println("Removing login failed message");
                        this.stage.getSignInPane().removeLoginFailedMessage();
                    }
                    System.out.println("Showing home scene");
                    this.stage.showHomeScene();
                } else if (validateAdmin(usernameInput, hashedPassword)) {
                    clearSignInInputs();
                    this.stage.showAdminHomeScene();
                } else {
                    // Validation failed
                    shakeNode(this.stage.getSignInPane().getUserField());
                    shakeNode(this.stage.getSignInPane().getPasswordField());
                    if (!this.stage.getSignInPane().isLogininFaildMessageVisible()) {
                        this.stage.getSignInPane().addLoginFailedMessage();
                    }
                    shakeNode(this.stage.getSignInPane().getLoginFailedMessage());
                    clearSignInInputs();
                }
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Hashing failed");
            }
        });

        // Eventhandler for the userField to jump to the passwordField
        this.stage.getSignInPane().getUserField().setOnAction(e -> {
            this.stage.getSignInPane().getPasswordField().requestFocus();
        });

        // Eventhandler for the passwordField to trigger the sign in button
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
        this.stage.getMenuPane().getOpenNewWindow().setOnAction(e -> {
            // TODO: Implement new window search
            Stage newStage = new Stage();
            Kappa kappa = new Kappa(newStage);
            KappaStage newKappaStage = new KappaStage(newStage);
            Main newWindow = new Main();
            newWindow.newSession(newStage);
        });

        // Eventhandler for the logOff button to show the sign in scene
        this.stage.getMenuPane().getLogOffButton().setOnAction(e -> {
            this.stage.showSignInSceneAfterLogout();
        });

        // Eventhandler for the searchCableTextField to search for a cable
        this.stage.getMenuPane().getSearchCableTextField().setOnAction(e -> {
            try{
                String cableId = this.stage.getMenuPane().getSearchCableTextField().getText();
                if(cableId.isEmpty() || cableId.isBlank() || cableId == null){
                    throw new Exception();
                }
                Cable cable = this.cableCoreDataDB.get(cableId);
                if (cable != null) {
                    System.out.println("Cable found, updatePVC method called");
                    updatePreviousViewedCables(cable);
                    this.stage.showCabelDetailScene(cable);
                }
                else{
                    throw new Exception();
                }
            }catch (Exception ex){
                shakeNode(this.stage.getMenuPane().getSearchCableTextField());
            }
        });
        
    }

    /**
     * 
     * @param cable to use for the Eventhandler
     * @param button to set an Eventhandler on
     */
    private void addCableToPreviousViewedCableHandler(Cable cable, Button button){
        System.out.println("adding Eventhandler to button");
        button.setOnAction(e -> {
            updatePreviousViewedCables(cable);
        });
    }


    /**
     * After clicking on a cable the previousViewedCables will be updated
     * @param cable that was clicked on
     */
    private void updatePreviousViewedCables(Cable cable){
        int actionCode = this.previousViewedCables.clickedOnCable(cable);
        System.out.println("actionCode is " + actionCode);
        if(actionCode == -1){
            // actionCode -1 means: cable is not in pvc and pvc is full
            Button b = this.previousViewedCablesPane.createPreviousViewedCableButton(cable);
            addCableToPreviousViewedCableHandler(cable, b);
            this.previousViewedCablesPane.removeOldestButton();
            this.previousViewedCablesPane.addButtonToVBox(b);

        } else if(actionCode == -2){
            // actionCode -2 means: cable not in pvc and pvc not full
            Button b = this.previousViewedCablesPane.createPreviousViewedCableButton(cable);
            addCableToPreviousViewedCableHandler(cable, b);
            this.previousViewedCablesPane.addButtonToVBox(b);
        } else{
            // actionCode postive number means: cable is already in pvc
            this.previousViewedCablesPane.putFirst(actionCode);
        }
        this.stage.updateKappa("Kappa - Kabel Detailansicht" + cable.getId());
    }

    /**
     * This method shakes a node to indicate that the input was invalid
     * 
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
     * This method clears the input fields userField and passwordField after a
     * failed login attempt
     */
    private void clearSignInInputs() {
        this.stage.getSignInPane().getUserField().clear();
        this.stage.getSignInPane().getPasswordField().clear();
    }

    /**
     * This method validates the user input with the admin credentials
     * 
     * @param usernameInput
     * @param passwordInput
     * @return true if the input is valid, false otherwise
     */
    private boolean validateAdmin(String usernameInput, String passwordInput) {
        return usernameInput.equals(User.getUsername()) && passwordInput.equals(User.getPasswort());
    }

    /**
     * This method validates the user input with the user credentials
     * 
     * @param username which will be compared with the username
     * @param Password which will be compared with the hashed password
     * @return true if the input is valid, false otherwise
     */
    private boolean validateUser(String username, String hashedPasswordInput) {
        return username.equals(User.getUsername()) && hashedPasswordInput.equals(User.getPasswort());
    }
}
