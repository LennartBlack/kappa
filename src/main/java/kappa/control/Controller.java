package kappa.control;

import java.security.NoSuchAlgorithmException;
import javafx.util.Duration;
import kappa.Kappa;
import kappa.model.Cable;
import kappa.model.CableCoreDataDB;
import kappa.model.PreviousViewedCable;
import kappa.model.User;
import kappa.model.Watchlist;
import kappa.utils.Hash;
import kappa.view.KappaStage;
import kappa.view.PreviousViewedCablesPane;
import kappa.view.TopWorkloadEntryPane;
import kappa.view.WatchlistEntryPane;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class Controller {

    // Attributes
    private KappaStage stage;
    private PreviousViewedCable previousViewedCables;
    private PreviousViewedCablesPane previousViewedCablesPane;
    private CableCoreDataDB cableCoreDataDB;
    private Watchlist watchlist;

    /**
     * Constructor for the Controller
     */
    public Controller(KappaStage stage, CableCoreDataDB cableCoreDataDB, Watchlist watchlist) {
        this.stage = stage;
        this.cableCoreDataDB = cableCoreDataDB;
        this.watchlist = watchlist;
        this.previousViewedCables = this.stage.getPreviousViewedCablesPane().getPreviousViewedCables();
        this.previousViewedCablesPane = this.stage.getPreviousViewedCablesPane();

        handleSignInScene();
        setSceneBuilderActions();
        handleCableSearch();
        topWorkloadCableHandling();

    }

    // Methods

    /**
     * This method sets the Eventhandlers for the SignInScene
     */
    private void handleSignInScene() {
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
                ex.printStackTrace();
            }
        });
        // Eventhandler for the userField to jump to the passwordField
        this.stage.getSignInPane().getUserField()
                .setOnAction(e -> this.stage.getSignInPane().getPasswordField().requestFocus());

        // Eventhandler for the passwordField to trigger the sign in button
        this.stage.getSignInPane().getPasswordField()
                .setOnAction(e -> this.stage.getSignInPane().getSignInButton().fire());
    }

    /**
     * This method sets the Eventhandlers to search for a cable
     */
    private void handleCableSearch() {
        // Eventhandler for the searchCableTextField to search for a cable
        this.stage.getMenuPane().getSearchCableTextField().setOnAction(e -> searchCable());
        this.stage.getMenuPane().getSearchCableButton().setOnAction(e -> searchCable());
    }

    /**
     * This method sets the Eventhandlers for the buttons to be able to switch
     * between the scenes
     */
    private void setSceneBuilderActions() {
        // Eventhandler for the help button to show the help scene
        this.stage.getMenuPane().getHelpButton().setOnAction(e -> this.stage.showHelpScene());

        // Eventhandler for the watchlist button to show the watchlist scene
        this.stage.getMenuPane().getWatchlistButton().setOnAction(e -> {
            this.stage.getWatchlistPane().constructWatchListPane();
            loadWatchlist(watchlist);
            this.stage.showWatchlistScene(cableCoreDataDB);
        });

        // Eventhandler for the topWorkloadCable button to topWorkloadCable the home
        // scene
        this.stage.getMenuPane().getTopWorkloadCableButton()
                .setOnAction(e -> this.stage.showCableWithTopWorkloudScene());

        // Eventhandler for the newWindowSearch button to show the newWindowSearch scene
        this.stage.getMenuPane().getOpenNewWindow().setOnAction(e -> {
            Kappa newWindow = new Kappa();
            newWindow.newSession();
        });

        // Eventhandler for the logOff button to show the sign in scene
        this.stage.getMenuPane().getLogOffButton().setOnAction(e -> this.stage.showSignInSceneAfterLogout());
    }

    /**
     * This method trys to search for a cable in the cableCoreDataDB HashMap,
     * When found the the cablePane will be shown with the cable data and
     * Eventhandlers for the buttons will be added.
     * When not found the searchCableTextField will be shaked
     * 
     * @throws Exception if the cable is not found and shakes the InputField
     */
    private void searchCable() {
        try {
            String input = this.stage.getMenuPane().getSearchCableTextField().getText();
            if (!validateInput(input)) {
                throw new Exception();
            }
            Cable cable = this.cableCoreDataDB.get(input);
            if (cable != null) {
                updatePreviousViewedCables(cable);

                // Hier passiert der fehler
                this.stage.showCablePane(cable);
                addGraphActionPaneEventHandlers(cable);
                this.stage.getWatchlistPane().constructWatchListPane();
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            shakeNode(this.stage.getMenuPane().getSearchCableTextField());
        }
    }

    /**
     * This method validates the user input to search for a cable
     * 
     * @param input user input to be validated as a form of a cable id
     * @return true if the input matches the pattern, false otherwise
     */
    private boolean validateInput(String input) {
        if (input.isEmpty() || input.isBlank() || input == null) {
            return false;
        }
        if (input.length() != 4) {
            return false;
        }
        if (!Character.isLetter(input.charAt(0)) || !Character.isLetter(input.charAt(1))
                || !Character.isDigit(input.charAt(2)) || !Character.isDigit(input.charAt(3))) {
            return false;
        }
        return true;
    }

    /**
     * This method loads WatchlistEntryPane for each Watchlist element and adds the
     * eventhandlers to the buttons
     * 
     * @param watchlist to be loaded into the WatchlistPane
     */
    private void loadWatchlist(Watchlist watchlist) {
        this.stage.getWatchlistPane().constructWatchListPane();
        // Iterate over the Children of the WatchlistPane
        for (int i = 0; i < this.stage.getWatchlistPane().getWatchlistVBox().getChildren().size(); i++) {
            // Check if the child is an WatchlistEntryPane
            if (this.stage.getWatchlistPane().getWatchlistVBox().getChildren().get(i) instanceof WatchlistEntryPane) {
                WatchlistEntryPane watchlistEntryPane = (WatchlistEntryPane) this.stage.getWatchlistPane()
                        .getWatchlistVBox().getChildren().get(i);
                String cableId = watchlistEntryPane.getWatchlistElement().getCable().getId();

                // Get Buttons from WatchlistEntryPane to add Eventhandlers
                Button cableIdButton = watchlistEntryPane.getCableIdButton();
                Button addNoteButton = watchlistEntryPane.getAddNoteButton();
                Button editNoteButton = watchlistEntryPane.getEditNoteButton();
                Button deleteNoteButton = watchlistEntryPane.getDeleteNoteButton();
                Button saveNoteButton = watchlistEntryPane.getSaveNoteButton();
                Button removeFromWatchlistButton = watchlistEntryPane.getRemoveFromWatchlistButton();

                // Add Eventhandlers to the Buttons
                cableIdButton.setOnAction(e -> {
                    Cable cable = cableCoreDataDB.get(cableId);
                    updatePreviousViewedCables(cable);
                    this.stage.showCablePane(cable);
                    addGraphActionPaneEventHandlers(cable);
                });
                addNoteButton.setOnAction(e -> {
                    watchlistEntryPane.createEditNotePane();
                    watchlistEntryPane.getNoteTextField().requestFocus();
                });
                editNoteButton.setOnAction(e -> {
                    watchlistEntryPane.createEditNotePane();
                    watchlistEntryPane.getNoteTextField().requestFocus();
                });
                deleteNoteButton.setOnAction(e -> {
                    watchlistEntryPane.getWatchlistElement().setNote("");
                    watchlist.getWatchlistElement(cableId).setNote("");
                    Watchlist.serializeHashMap(watchlist);
                    watchlistEntryPane.createPaneWithoutNote();
                });
                saveNoteButton.setOnAction(e -> {
                    String note = watchlistEntryPane.getNoteTextField().getText();
                    watchlistEntryPane.getWatchlistElement().setNote(note);
                    watchlist.getWatchlistElement(cableId).setNote(note);
                    Watchlist.serializeHashMap(watchlist);
                    watchlistEntryPane.createPaneWithNote();
                });
                removeFromWatchlistButton.setOnAction(e -> {
                    watchlist.removeCable(watchlistEntryPane.getWatchlistElement().getCable());
                    Watchlist.serializeHashMap(watchlist);
                    this.stage.getWatchlistPane().removeWatchlistEntryPane(watchlistEntryPane);
                    this.stage.showWatchlistScene(cableCoreDataDB);
                });
                watchlistEntryPane.getNoteTextField().setOnAction(e -> saveNoteButton.fire());
            }
        }
        this.stage.showWatchlistScene(cableCoreDataDB);
    }

    /**
     * This method adds the Eventhandlers for the Buttons to add and remove a cable
     * from the watchlist
     * 
     * @param cable to be added
     */
    private void addGraphActionPaneEventHandlers(Cable cable) {
        try {
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getAddToWatchlistButton()
                    .setOnAction(e -> {
                        watchlist.addCable(cable);
                        Watchlist.serializeHashMap(watchlist);
                        this.stage.getWatchlistPane().constructWatchListPane();
                        this.stage.getCablePane().getCableDetailPane().getGraphActionPane()
                                .updateWatchlistButtons(cable);
                    });
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getRemoveFromWatchlistButton()
                    .setOnAction(e -> {
                        watchlist.removeCable(cable);
                        Watchlist.serializeHashMap(watchlist);
                        this.stage.getWatchlistPane().removeWatchlistEntryPane(cable.getId());
                        this.stage.getCablePane().getCableDetailPane().getGraphActionPane()
                                .updateWatchlistButtons(cable);
                    });
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getFullRecordTime()
                    .setOnAction(e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().showMaxPeriod());
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getPreviousPeriod()
                    .setOnAction(e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().showPrevPeriod());
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getNextPeriod()
                    .setOnAction(e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().showNxtPerioud());
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getFiveDaysButton().setOnAction(
                    e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().setPeriod("5 Tage"));
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getTenDaysButton().setOnAction(
                    e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().setPeriod("10 Tage"));
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getOneMonthButton().setOnAction(
                    e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().setPeriod("1 Monat"));
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getThreeMonthButton().setOnAction(
                    e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().setPeriod("3 Monate"));
            this.stage.getCablePane().getCableDetailPane().getGraphActionPane().getSixMonthButton().setOnAction(
                    e -> this.stage.getCablePane().getCableDetailPane().getGraphPane().setPeriod("6 Monate"));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        this.stage.updateKappa("Kappa - Kabel Detailansicht " + cable.getId());
    }

    /**
     * This method shakes a node to indicate that the input was invalid
     * 
     * @param node to be shaken
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

    /**
     * This method adds the Eventhandlers for the topWorkloadCablePane
     */
    private void topWorkloadCableHandling() {
        
        for (int i = 0; i < (this.stage.getTopWorkloadCablePane().getTopWorkloadVBox().getChildren().size()); i++) {
            if (this.stage.getTopWorkloadCablePane().getTopWorkloadVBox().getChildren().get(i) instanceof TopWorkloadEntryPane) {
                TopWorkloadEntryPane topWorkloadEntryPane = (TopWorkloadEntryPane) this.stage.getTopWorkloadCablePane().getTopWorkloadVBox().getChildren().get(i);
                topWorkloadEntryPane.getCableIdButton().setOnAction(e ->{
                    Cable cable = cableCoreDataDB.get(topWorkloadEntryPane.getCable().getId());
                    updatePreviousViewedCables(cable);
                    this.stage.showCablePane(cable);
                    addGraphActionPaneEventHandlers(cable);
                });
            }
        }
    }
}
