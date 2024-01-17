package controller;

import model.Callback;
import model.EmployeeInformation;
import model.HomePageModel;
import view.HomePageView;

public class HomePageController implements Callback {
    HomePageView homePageView;
    HomePageModel homePageModel;
    public HomePageController(HomePageView _homePageView, HomePageModel _homePageModel) {
        this.homePageView = _homePageView;
        this.homePageModel = _homePageModel;

        this.homePageModel.getAuthenticationService().addCallback(this::execute);
    }

    @Override
    public void execute() {
        EmployeeInformation employeeInformation = this.homePageModel.getEmployeeInformation();
        if (employeeInformation != null) {
            this.homePageView.initEmployeeInformation(employeeInformation);
        }
    }
}
