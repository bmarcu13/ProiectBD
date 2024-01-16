package controller.hr;

import controller.financial.EveryoneController;
import controller.financial.ExpertController;
import controller.financial.MedicController;
import model.AuthenticationService;
import model.financial.EveryoneModel;
import model.financial.ExpertModel;
import model.hr.HRMedicalModel;
import model.hr.InspectorModel;
import view.financial.EveryoneView;
import view.financial.ExpertView;
import view.financial.MedicView;
import view.hr.HRManagementView;
import model.Callback;
import view.hr.HRMedicalView;
import view.hr.InspectorView;

public class HRManagementController implements Callback {
    AuthenticationService authenticationService;
    HRManagementView hrManagementView;
    InspectorView inspectorView;
    InspectorController inspectorController;

    HRMedicalView hrMedicalView;
    HRMedicalController hrMedicalController;
    public HRManagementController(HRManagementView _hrManagementView, AuthenticationService _authenticationService) {
        this.hrManagementView = _hrManagementView;
        this.authenticationService = _authenticationService;
        this.authenticationService.addCallback(this::loadInnerPage);
    }

    public void loadInnerPage() {
        switch (this.authenticationService.getAccountType()) {
            case AuthenticationService.ACC_HR:
            case AuthenticationService.ACC_FINANCIAL:
                this.inspectorView = new InspectorView();
                this.inspectorController = new InspectorController(this.inspectorView, new InspectorModel());
                this.hrManagementView.addInspectorView(this.inspectorView);
                break;
            default:
                this.hrMedicalView = new HRMedicalView();
                this.hrManagementView.addMedicalView(this.hrMedicalView);
                this.hrMedicalController = new HRMedicalController(this.hrMedicalView, new HRMedicalModel(this.authenticationService));
                break;
        }
    }

    @Override
    public void execute() {
    }
}
