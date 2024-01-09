package controller.financial;

import model.AuthenticationService;
import model.DatabaseService;
import model.financial.MedicProfitsModel;
import view.financial.MedicProfitsView;
import view.financial.MedicView;

import java.sql.SQLException;
import java.util.ArrayList;

public class MedicController {
    DatabaseService databaseService = DatabaseService.getInstance();
    AuthenticationService authenticationService;
    private final MedicView medicView;
    private final MedicProfitsController medicProfitsController;
    private final EveryoneController everyoneController;
    MedicController(MedicView _medicView, EveryoneController _everyoneController, AuthenticationService _authenticationService) {
        this.authenticationService = _authenticationService;

        this.medicView = _medicView;
        this.everyoneController = _everyoneController;

        this.medicProfitsController = new MedicProfitsController(this.medicView.getMedicProfitsView(), new MedicProfitsModel(this.authenticationService));

    }
}
