package controller.financial;

import model.AuthenticationService;
import model.Callback;
import model.financial.EveryoneModel;
import view.financial.EveryoneView;
import view.financial.ExpertView;
import view.financial.FinancialView;
import view.financial.MedicView;

public class FinancialController implements Callback {
    private FinancialView financialView;
    private AuthenticationService authenticationService;

    private EveryoneView everyoneView;
    private ExpertView expertView;
    private MedicView medicView;
    private EveryoneController everyoneController;

    private MedicController medicController;

    public FinancialController(FinancialView _financialView, AuthenticationService _authenticationService) {
        this.financialView = _financialView;
        this.authenticationService = _authenticationService;
        this.authenticationService.addCallback(this::loadInnerPage);
    }

    public void loadInnerPage() {
        switch (this.authenticationService.getAccountType()) {
            case AuthenticationService.ACC_FINANCIAL:
                this.expertView = new ExpertView();
                this.financialView.addExpertView(this.expertView);
                break;
            case AuthenticationService.ACC_MEDICAL_DOCTOR:
                this.everyoneView = new EveryoneView();
                this.everyoneController = new EveryoneController(this.everyoneView, new EveryoneModel(this.authenticationService));
                this.medicView = new MedicView(this.everyoneView);
                this.medicController = new MedicController(this.medicView, this.everyoneController, this.authenticationService);
                this.financialView.addMedicView(this.medicView);
                break;
            default:
                this.everyoneView = new EveryoneView();
                this.everyoneController = new EveryoneController(this.everyoneView, new EveryoneModel(this.authenticationService));
                this.financialView.addEveryoneView(this.everyoneView);
                break;
        }
    }

    @Override
    public void execute() {
    }
}