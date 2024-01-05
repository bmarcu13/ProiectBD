package controller;

import model.AuthenticationService;
import model.Callback;
import view.financial.EveryoneView;
import view.financial.ExpertView;
import view.financial.FinancialView;
import view.financial.MedicView;

public class FinancialController implements Callback {
    private FinancialView financialView;
    private AuthenticationService authenticationService;
    public FinancialController(FinancialView _financialView, AuthenticationService _authenticationService) {
        this.financialView = _financialView;
        this.authenticationService = _authenticationService;
        this.authenticationService.addCallback(this::loadInnerPage);
    }

    public void loadInnerPage() {
        switch (this.authenticationService.getAccountType()) {
            case AuthenticationService.ACC_FINANCIAL:
                this.financialView.addExpertView(new ExpertView());
                break;
            case AuthenticationService.ACC_MEDICAL_DOCTOR:
                this.financialView.addMedicView(new MedicView());
                break;
            default:
                this.financialView.addEveryoneView(new EveryoneView());
                break;
        }
    }

    @Override
    public void execute() {
    }
}