package model;

import model.hr.EmployeeVacation;
import model.hr.GenericTimetable;
import model.hr.SpecificTimetable;

import java.net.PasswordAuthentication;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class DatabaseService {
	private static DatabaseService databaseService = null;
	
	private final String URL = "jdbc:mysql://localhost:3306/policlinica";
	private final String USERNAME = "policlinica_generic_user";
	private final String GENERIC_PASSWORD = "Strongpassword";
	
	private Connection connection;
	private PermissionService permissionService = PermissionService.getInstance(); 
	
	private DatabaseService() {}
	
	public static DatabaseService getInstance()
	{
		if(databaseService == null)
		{
			databaseService = new DatabaseService();
		}
		return databaseService;
	}
	
	public void connectToDatabaseGeneric() throws SQLException
	{
		connection = DriverManager.getConnection(URL, USERNAME, GENERIC_PASSWORD);
	}
	
	public void connectToDatabaseWithCredentials(Credentials credentials) throws SQLException
	{
		connection.close();
		connection = DriverManager.getConnection(URL, credentials.getUsername(), credentials.getPassword());
	}
	
	public Credentials getAccountDetails(Credentials credentials) throws SQLException
	{
		System.out.println("Yo Yo Yo " + credentials.getUsername());
		String statement = "CALL get_account_credentials(?, ?, ?, ?, ?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setString(1, credentials.getUsername());
		cs.setString(2, credentials.getPassword());
		cs.registerOutParameter(3, java.sql.Types.INTEGER);
		cs.registerOutParameter(4, java.sql.Types.VARCHAR);
		cs.registerOutParameter(5, java.sql.Types.VARCHAR);
		
		cs.execute();
		
		int response = cs.getInt(3);
		String id = null, password = null;
		
		if(response == 200)
		{
			id = cs.getString(4);
			password = cs.getString(5);
		}
		
		return new Credentials(id, password);
	}
	
	public Vector<Doctor> getDoctorList() throws SQLException
	{
		String statement = "CALL get_all_doctors()";
		CallableStatement cs = connection.prepareCall(statement);
		
		ResultSet resultSet = cs.executeQuery();
		
		Vector<Doctor> doctors = new Vector<Doctor>();
		
		while(resultSet.next())
		{
			doctors.add(new Doctor(resultSet.getNString(1), resultSet.getNString(2), resultSet.getNString(3)));
		}
		
		return doctors;
	}
	
	public Vector<MedicalService> getMedicalServicesForMedic(String cnp) throws SQLException
	{
		Vector<MedicalService> services = new Vector<MedicalService>();
		
		String statement = "CALL get_specializations_for_doctor(?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setString(1, cnp);
		
		ResultSet resultSet = cs.executeQuery();
		
		while(resultSet.next())
		{
			services.add(new MedicalService(resultSet.getInt(1), resultSet.getNString(2), resultSet.getTime(3).toLocalTime(), resultSet.getInt(4)));
		}
		
		return services;
	}

	public int createAppointment(String patientCNP, String patientFirstName, String patientSecondName, String doctorCNP, LocalDate date, LocalTime time, LocalTime duration) throws SQLException
	{
		String statement = "CALL create_appointment(?, ?, ?, ?, ?, ?, ?, ?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setString(1, patientCNP);
		cs.setString(2, patientFirstName);
		cs.setString(3, patientSecondName);
		cs.setString(4, doctorCNP);
		cs.setDate(5, Date.valueOf(date));
		cs.setTime(6, Time.valueOf(time));
		cs.setTime(7, Time.valueOf(duration));
		cs.registerOutParameter(8, java.sql.Types.INTEGER);
		
		cs.execute();
		
		return cs.getInt(8);
	}
	
	public void createServiceAppointment(int appointmentId, int medicalServiceId) throws SQLException
	{
		String statement = "CALL create_service_appointment(?, ?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setInt(1, appointmentId);
		cs.setInt(2, medicalServiceId);
		
		cs.execute();
	}
	
	public Vector<Appointment> getUnregisteredAppointments() throws SQLException
	{
		String statement = "CALL get_unregistered_appointments()";
		CallableStatement cs = connection.prepareCall(statement);
		
		ResultSet results = cs.executeQuery();
		Vector<Appointment> appointments = new Vector<Appointment>();
		
		while(results.next())
		{
			appointments.add(new Appointment(results.getInt(1), results.getNString(2), results.getNString(3), results.getTime(4).toLocalTime()));
		}
		
		return appointments;
	}
	public int getEmployeeEarnings(String _cnp, Date _date) throws SQLException {
		String statement = "SELECT get_employee_earnings(?, ?) AS earnings";
		PreparedStatement preparedStatement= connection.prepareStatement(statement);

		preparedStatement.setString(1, _cnp);
		preparedStatement.setDate(2, _date);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("earnings");
		}
		return 0;
	}

	public String getUserCnp(String userEmail) throws SQLException {
		String statement = "SELECT get_user_cnp(?) AS user_cnp";
		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, userEmail);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("user_cnp");
		}
		return "";
	}

	public ArrayList<Integer> getMedicWorkingUnits(String medicCNP) throws SQLException {
		String statement = "CALL get_medic_working_units(?)";
		CallableStatement cs = connection.prepareCall(statement);

		cs.setString(1, medicCNP);

		ResultSet resultSet = cs.executeQuery();

		ArrayList<Integer> medicalUnitIDs = new ArrayList<>();
		while (resultSet.next()) {
			medicalUnitIDs.add(resultSet.getInt(1));
		}
		return medicalUnitIDs;
	}

	public String getMedicalUnitName(int medicalUnitID) throws SQLException {
		String statement = "SELECT get_medical_unit_name(?) AS medical_unit_name";
		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setInt(1, medicalUnitID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("medical_unit_name");
		}
		return "";
	}

	public int getMedicPaidServicesProfit(String medicCNP, Date date, int medicalUnitID) throws SQLException {
		String statement = "SELECT get_medic_paid_services_profit(?, ?, ?) AS profit";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, medicCNP);
		preparedStatement.setDate(2, date);
		preparedStatement.setInt(3, medicalUnitID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("profit");
		}
		return 0;
	}

	public int getMedicProfitOnOneUnit(String medicCNP, Date date, int medicalUnitID) throws SQLException {
		String statement = "SELECT get_medic_profit_on_one_unit(?, ?, ?) AS profit";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, medicCNP);
		preparedStatement.setDate(2, date);
		preparedStatement.setInt(3, medicalUnitID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("profit");
		}
		return 0;
	}

	public int getMedicalUnitIncome(Date date, int medicalUnitID) throws SQLException {
		String statement = "SELECT get_medical_unit_income(?, ?) AS income";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setDate(1, date);
		preparedStatement.setInt(2, medicalUnitID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("income");
		}
		return 0;
	}

	public int getMedicalUnitExpenses(Date date, int medicalUnitID) throws SQLException {
		String statement = "SELECT get_medical_unit_expenses(?, ?) AS expenses";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setDate(1, date);
		preparedStatement.setInt(2, medicalUnitID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("expenses");
		}
		return 0;
	}

	public int getMedicalUnitProfit(Date date, int medicalUnitID) throws SQLException {
		String statement = "SELECT get_medical_unit_profit(?, ?) AS profit";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setDate(1, date);
		preparedStatement.setInt(2, medicalUnitID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("profit");
		}
		return 0;
	}

	public HashMap<Integer, String> getAllMedicalUnits() throws SQLException {
		String statement = "CALL get_all_medical_units()";
		CallableStatement cs = connection.prepareCall(statement);

		ResultSet resultSet = cs.executeQuery();

		HashMap<Integer, String> medicalUnits = new HashMap<>();
		while (resultSet.next()) {
			medicalUnits.put(resultSet.getInt("id_unitate_medicala"), resultSet.getString("denumire_unitate_medicala"));
		}
		return medicalUnits;
	}

	public Vector<String> getAllMedicNames() throws SQLException {
		String statement = "CALL get_all_medic_names()";
		CallableStatement cs = connection.prepareCall(statement);

		ResultSet resultSet = cs.executeQuery();

		Vector<String> medicNames = new Vector<>();
		while (resultSet.next()) {
			medicNames.add(resultSet.getString("nume"));
		}
		return medicNames;
	}

	public String getMedicCnpByName(String _name, String _surname) throws SQLException {
		String statement = "SELECT get_medic_cnp_by_name(?, ?) AS cnp_medic";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, _name);
		preparedStatement.setString(2, _surname);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("cnp_medic");
		}
		return "";
	}

	public Vector<String> getAllEmployeeNames() throws SQLException {
		String statement = "CALL get_all_employee_names()";
		CallableStatement cs = connection.prepareCall(statement);

		ResultSet resultSet = cs.executeQuery();

		Vector<String> employeeNames = new Vector<>();
		while (resultSet.next()) {
			employeeNames.add(resultSet.getString("nume"));
		}
		return employeeNames;
	}

	public Vector<String> getAllRanks(String _name, String _surname) throws SQLException {
		String statement = "CALL get_all_ranks(?, ?)";
		CallableStatement cs = connection.prepareCall(statement);

		cs.setString(1, _name);
		cs.setString(2, _surname);
		ResultSet resultSet = cs.executeQuery();

		Vector<String> rankNames = new Vector<>();
		while (resultSet.next()) {
			rankNames.add(resultSet.getString("denumire_functie"));
		}
		return rankNames;
	}

	public String getEmployeeCnp(String _name, String _surname, String _rank) throws SQLException {
		String statement = "SELECT get_employee_cnp(?, ?, ?) AS cnp_employee";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, _name);
		preparedStatement.setString(2, _surname);
		preparedStatement.setString(3, _rank);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("cnp_employee");
		}
		return "";
	}

	public Vector<GenericTimetable> getEmployeeGenericTimetable(String _name, String _surname, String _rank) throws SQLException {
		String statement = "CALL get_employee_generic_timetable(?)";
		CallableStatement cs = connection.prepareCall(statement);

		cs.setString(1, this.getEmployeeCnp(_name, _surname, _rank));

		ResultSet resultSet = cs.executeQuery();

		Vector<GenericTimetable> genericTimetable = new Vector<>();
		while (resultSet.next()) {
			genericTimetable.add(new GenericTimetable(resultSet.getString("zi"), resultSet.getTime("ora_incepere"),
					resultSet.getTime("ora_terminare"), resultSet.getString("denumire_unitate_medicala")));
		}
		return genericTimetable;
	}

	public Vector<SpecificTimetable> getEmployeeSpecificTimetable(String _name, String _surname, String _rank, Date _month) throws SQLException {
		String statement = "CALL get_employee_specific_timetable(?, ?)";
		CallableStatement cs = connection.prepareCall(statement);

		cs.setString(1, this.getEmployeeCnp(_name, _surname, _rank));
		cs.setDate(2, _month);

		ResultSet resultSet = cs.executeQuery();

		Vector<SpecificTimetable> specificTimetable = new Vector<>();
		while (resultSet.next()) {
			specificTimetable.add(new SpecificTimetable(resultSet.getDate("data"), resultSet.getTime("ora_incepere"),
					resultSet.getTime("ora_terminare"), resultSet.getString("denumire_unitate_medicala")));
		}
		return specificTimetable;
	}

	public Vector<EmployeeVacation> getEmployeeVacations(String _name, String _surname, String _rank, Date _month) throws SQLException {
		String statement = "CALL get_employee_vacations(?, ?)";
		CallableStatement cs = connection.prepareCall(statement);

		cs.setString(1, this.getEmployeeCnp(_name, _surname, _rank));
		cs.setDate(2, _month);

		ResultSet resultSet = cs.executeQuery();

		Vector<EmployeeVacation> employeeVacations = new Vector<>();
		while (resultSet.next()) {
			employeeVacations.add(new EmployeeVacation(resultSet.getDate("data_incepere"), resultSet.getDate("data_terminare")));
		}
		return employeeVacations;
	}

	public String getEmployeeName(String _employeeCnp) throws SQLException {
		String statement = "SELECT get_employee_name(?) AS employee_name";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, _employeeCnp);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("employee_name");
		}
		return "";
	}

	public String getEmployeeRank(String _employeeCnp) throws SQLException {
		String statement = "SELECT get_employee_rank(?) AS employee_rank";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, _employeeCnp);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("employee_rank");
		}
		return "";
	}

	public EmployeeInformation getEmployeeInformation(String _employeeCnp) throws SQLException {
		String statement = "CALL get_employee_information(?)";
		CallableStatement cs = connection.prepareCall(statement);

		cs.setString(1, _employeeCnp);

		ResultSet resultSet = cs.executeQuery();

		if (resultSet.next()) {
			return new EmployeeInformation(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
					resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
					resultSet.getString(9), resultSet.getInt(10), resultSet.getDate(11),
					resultSet.getInt(12), resultSet.getInt(13));
		}
		return null;
	}

	public int getMedicCommission(String _medicCnp) throws SQLException {
		String statement = "SELECT get_commission(?) AS commission";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);

		preparedStatement.setString(1, _medicCnp);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("commission");
		}
		return 0;
	}



}
