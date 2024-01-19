package model;

import java.sql.SQLException;

public class HomePageModel {
    AuthenticationService authenticationService;
    DatabaseService databaseService = DatabaseService.getInstance();
    public HomePageModel(AuthenticationService _authenticationService) {
        this.authenticationService = _authenticationService;
    }

    public EmployeeInformation getEmployeeInformation() {
        try {
            return this.databaseService.getEmployeeInformation(this.databaseService.getUserCNP(this.authenticationService.getEmail()));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public AuthenticationService getAuthenticationService() {
        return this.authenticationService;
    }
}
