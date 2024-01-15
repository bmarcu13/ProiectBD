package controller.financial;

import model.AuthenticationService;
import model.Callback;
import model.financial.EveryoneModel;
import model.financial.ExpertModel;
import view.financial.EveryoneView;
import view.financial.ExpertView;
import view.financial.FinancialView;
import view.financial.MedicView;

public class FinancialController implements Callback {
    private FinancialView financialView;
    private AuthenticationService authenticationService;

    private EveryoneView everyoneView;
    private EveryoneController everyoneController;
    private MedicView medicView;
    private MedicController medicController;
    private ExpertView expertView;
    private ExpertController expertController;


    public FinancialController(FinancialView _financialView, AuthenticationService _authenticationService) {
        this.financialView = _financialView;
        this.authenticationService = _authenticationService;
        this.authenticationService.addCallback(this::loadInnerPage);
    }

    public void loadInnerPage() {
        this.everyoneView = new EveryoneView();
        switch (this.authenticationService.getAccountType()) {
            case AuthenticationService.ACC_FINANCIAL:
                this.everyoneController = new EveryoneController(this.everyoneView, new EveryoneModel(this.authenticationService));
                this.expertView = new ExpertView(this.everyoneView);
                this.expertController = new ExpertController(this.expertView, new ExpertModel());
                this.financialView.addExpertView(this.expertView);
                break;
            case AuthenticationService.ACC_MEDICAL_DOCTOR:
                this.everyoneController = new EveryoneController(this.everyoneView, new EveryoneModel(this.authenticationService));
                this.medicView = new MedicView(this.everyoneView);
                this.medicController = new MedicController(this.medicView, this.everyoneController, this.authenticationService);
                this.financialView.addMedicView(this.medicView);
                break;
            default:
                this.everyoneController = new EveryoneController(this.everyoneView, new EveryoneModel(this.authenticationService));
                this.financialView.addEveryoneView(this.everyoneView);
                break;
        }
    }

    @Override
    public void execute() {
    }
}