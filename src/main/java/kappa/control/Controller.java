package kappa.control;

import javafx.stage.Stage;
import kappa.model.Kappa;
import kappa.view.KappaStage;

public class Controller {
    private KappaStage stage;
    public Controller(KappaStage stage){
        this.stage = stage;

        this.stage.getLoginRegisterPane().getSignInButton().setOnAction(e -> {
            //Validate Usercredintials
            this.stage.getLoginRegisterPane().getPasswordInput();
            this.stage.getLoginRegisterPane().getUsernameInput();

            //Validate Admincredintials
        });
    }
}
