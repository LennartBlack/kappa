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
import kappa.model.Watchlist;
import kappa.utils.Hash;
import kappa.view.KappaStage;
import kappa.view.PreviousViewedCablesPane;
import kappa.view.WatchlistEntryPane;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Controller {
    private KappaStage stage;
    private PreviousViewedCable previousViewedCables;
    private PreviousViewedCablesPane previousViewedCablesPane;
    private CableCoreDataDB cableCoreDataDB;
    private Watchlist watchlist;

    public Controller(KappaStage stage, CableCoreDataDB cableCoreDataDB, Watchlist watchlist) {
        this.stage = stage;
        this.cableCoreDataDB = cableCoreDataDB;
        this.watchlist = watchlist;
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
                        this.stage.getSignInPane().removeLoginFailedMessage();
                    }
                    this.stage.showHomeScene();
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
            this.stage.showWatchlistScene(cableCoreDataDB);
            loadWatchlist(watchlist);

        });

        // Eventhandler for the topWorkloadCable button to topWorkloadCable the home
        // scene
        this.stage.getMenuPane().getTopWorkloadCableButton().setOnAction(e -> {
            this.stage.showCableWithTopWorkloudScene();
        });

        // Eventhandler for the newWindowSearch button to show the newWindowSearch scene
        this.stage.getMenuPane().getOpenNewWindow().setOnAction(e -> {
            // TODO: Implement new window search
            Stage newStage = new Stage();
            Kappa kappa = new Kappa(newStage);
            KappaStage newKappaStage = new KappaStage(newStage, cableCoreDataDB, watchlist);
            Main newWindow = new Main();
            newWindow.newSession(newStage);
        });

        // Eventhandler for the logOff button to show the sign in scene
        this.stage.getMenuPane().getLogOffButton().setOnAction(e -> {
            this.stage.showSignInSceneAfterLogout();
        });

        // Eventhandler for the searchCableTextField to search for a cable
        this.stage.getMenuPane().getSearchCableTextField().setOnAction(e -> {
            try {
                String cableId = this.stage.getMenuPane().getSearchCableTextField().getText();
                if (cableId.isEmpty() || cableId.isBlank() || cableId == null) {
                    throw new Exception();
                }
                Cable cable = this.cableCoreDataDB.get(cableId);
                if (cable != null) {
                    updatePreviousViewedCables(cable);
                    this.stage.showCablePane(cable);
                    addGraphActionPaneEventHandlers(cable);
                } else {
                    throw new Exception();
                }
            } catch (Exception ex) {
                shakeNode(this.stage.getMenuPane().getSearchCableTextField());
            }
        });

    }

    private void loadWatchlist(Watchlist watchlist) {
        for (int i = 0; i < this.stage.getWatchlistPane().getWatchlistVBox().getChildren().size(); i++) {
            if (this.stage.getWatchlistPane().getWatchlistVBox().getChildren().get(i) instanceof HBox) {
                WatchlistEntryPane watchlistEntryPane = (WatchlistEntryPane) this.stage.getWatchlistPane()
                        .getWatchlistVBox().getChildren().get(i);
                String cableId = ((Label) watchlistEntryPane.getChildren().get(0)).getText();
                Button button = (Button) watchlistEntryPane.getChildren().get(1);
                button.setOnAction(e -> {
                    System.out.println("Buttonaction used");
                    watchlistEntryPane.updateWatchlistEntryPane();
                });

            }
        }
        this.stage.showWatchlistScene(cableCoreDataDB);
    }

    private void addGraphActionPaneEventHandlers(Cable cable) {
        this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getAddToWatchlistButton()
                .setOnAction(e -> {
                    watchlist.addCable(cable);
                    Watchlist.serializeHashMap(watchlist);
                });

        this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getRemoveFromWatchlistButton()
                .setOnAction(e -> {
                    watchlist.removeCable(cable);
                    Watchlist.serializeHashMap(watchlist);
                });
    }

    /**
     * 
     * @param cable  to use for the Eventhandler
     * @param button to set an Eventhandler on
     */
    private void addCableToPreviousViewedCableHandler(Cable cable, Button button) {
        button.setOnAction(e -> {
            updatePreviousViewedCables(cable);
            this.stage.showCablePane(cable);
            addGraphActionPaneEventHandlers(cable);
        });
    }

    /**
     * After clicking on a cable the previousViewedCables will be updated
     * 
     * @param cable that was clicked on
     */
    private void updatePreviousViewedCables(Cable cable) {
        if (this.previousViewedCables.checkIfCableIsInPreviousViewedCables(cable)) {
            if (this.previousViewedCables.isCableLatestItem(cable)) {
                // cable is in previousViewedCables and is the latest item
            } else {
                // cable is in previousViewedCables but not the latest item
                int oldIndex = this.previousViewedCables.getIndexOFCable(cable);
                this.previousViewedCablesPane.putButtonOnTopOfList(oldIndex);
                this.previousViewedCables.putCableOnTopOfList(oldIndex, cable);
            }
        } else {
            if (this.previousViewedCables.isPreviousViewedCablesFull()) {
                // cable is not in previousViewedCables and previousViewedCables is full
                addCableToPreviousViewedCableHandler(cable,
                        this.previousViewedCablesPane.createPreviousViewedCableButton(cable));
                this.previousViewedCablesPane.removeOldestButton();
                this.previousViewedCables.addFirst(cable);
                this.previousViewedCables.removeLast();
            } else {
                // cable is not in previousViewedCables and previousViewedCables is not full
                addCableToPreviousViewedCableHandler(cable,
                        this.previousViewedCablesPane.createPreviousViewedCableButton(cable));
                this.previousViewedCables.addFirst(cable);
            }
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
