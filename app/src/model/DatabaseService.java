package model;

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

}
