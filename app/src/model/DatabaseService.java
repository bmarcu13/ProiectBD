package model;

import java.net.PasswordAuthentication;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import com.mysql.cj.xdevapi.Result;

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
	
	public Vector<Appointment> getRegisteredAppointments() throws SQLException
	{
		String statement = "CALL get_registered_appointments()";
		CallableStatement cs = connection.prepareCall(statement);
		
		ResultSet results = cs.executeQuery();
		Vector<Appointment> appointments = new Vector<Appointment>();
		
		while(results.next())
		{
			appointments.add(new Appointment(results.getInt(1), results.getNString(2), results.getNString(3), results.getTime(4).toLocalTime()));
		}
		
		return appointments;
	}
	
	public void registerPatientForAppointment(int appointmentId) throws SQLException
	{
		String statement = "CALL register_patient_for_appointment(?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setInt(1, appointmentId);
		
		cs.execute();
	}
	
	public Vector<Investigation> getAvailableInvestigations() throws SQLException
	{
		String statement = "CALL get_available_investigations()";
		CallableStatement cs = connection.prepareCall(statement);
		
		ResultSet results = cs.executeQuery();
		
		Vector<Investigation> investigationList = new Vector<Investigation>();
		
		while (results.next())
		{
			int lowerBound = results.getInt(3);
			int upperBound = results.getInt(4);
			if(results.wasNull())
			{				
				investigationList.add(new Investigation(results.getInt(1), results.getNString(2)));
			}
			else
			{
				investigationList.add(new Investigation(results.getInt(1), results.getNString(2), lowerBound, upperBound));
			}
		}
		
		return investigationList;
	}
	
	public Vector<Investigation> getExistingInvestigations(int appointmentId) throws SQLException
	{
		String statement = "CALL get_existing_investigations(?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setInt(1, appointmentId);
		
		ResultSet results = cs.executeQuery();
		
		Vector<Investigation> existingInvestigations = new Vector<Investigation>();
		
		while(results.next())
		{
			Investigation investigation = new Investigation(results.getInt(1), results.getNString(2));
			int val = results.getInt(3);
			if(results.wasNull())
			{				
				investigation.setValue(results.getByte(4));
			}
			else
			{
				investigation.setValue(val);
			}
			
			existingInvestigations.add(investigation);
		}
		
		return existingInvestigations;
	}
	
	public void insertInvestigations(int appointmentId, Investigation investigation) throws SQLException
	{
		String statement = "CALL insert_investigation_result(?, ?, ?, ?)";
		CallableStatement cs = connection.prepareCall(statement);
				
		cs.setInt(1, investigation.getId());
		cs.setInt(2, appointmentId);
		if(investigation.getValue() == 1 || investigation.getValue() == 0)
		{			
			cs.setNull(3, java.sql.Types.INTEGER);
			cs.setByte(4, (byte) investigation.getValue());
		}
		else
		{
			cs.setInt(3, investigation.getValue());
			cs.setNull(4, java.sql.Types.TINYINT);
		}
		
		cs.execute();
	}
	
	public Vector<Appointment> getAppointments(String doctorCnp, LocalDate date) throws SQLException
	{
		String statement = "CALL get_appointments(?, ?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setDate(1, Date.valueOf(date));
		cs.setNString(2, doctorCnp);
		
		ResultSet results = cs.executeQuery();
		
		Vector<Appointment> appointments = new Vector<Appointment>();
		
		while(results.next())
		{
			appointments.add(new Appointment(results.getInt(1),	results.getNString(2), results.getNString(3), results.getTime(4).toLocalTime(), results.getBoolean(5)));
		}
		
		return appointments;
	}
	
	public String getUserCNP(String email) throws SQLException
	{
		String statement = "CALL get_user_cnp(?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setNString(1, email);
		ResultSet results = cs.executeQuery();
		
		results.next();
		
		return results.getNString(1);
	}
	
	public Vector<Investigation> getExistingServices(int appointmentId) throws SQLException
	{
		String statement = "CALL get_existing_services(?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setInt(1, appointmentId);
		
		ResultSet results = cs.executeQuery();
		
		Vector<Investigation> existingServices = new Vector<Investigation>();
		
		while(results.next())
		{
			existingServices.add(new Investigation(results.getInt(2), results.getNString(1)));
		}
		
		return existingServices;
	}
	
	public Vector<Investigation> getSelectableServices(String doctorCNP) throws SQLException
	{
		String statement = "CALL get_selectable_services(?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setNString(1, doctorCNP);
		ResultSet results = cs.executeQuery();
		
		Vector<Investigation> selectableServices = new Vector<Investigation>();
		
		while(results.next())
		{
			selectableServices.add(new Investigation(results.getInt(2), results.getNString(1)));
		}
		
		return selectableServices;
	}
	
	public String getDoctorName(String doctorCNP) throws SQLException
	{
		String statement = "SELECT CONCAT(nume, ' ', prenume) FROM angajat where cnp_angajat =" + doctorCNP;
		CallableStatement cs = connection.prepareCall(statement);
		
		ResultSet results = cs.executeQuery();
		results.next();
		return results.getNString(1);
	}
	
	public void insertMedicalReport(MedicalReport mr) throws SQLException
	{
		String statement = "CALL insert_medical_report(?, ?, ?, ?, ?, ?, ?, ?)";
		CallableStatement cs = connection.prepareCall(statement);
		
		cs.setInt(1, mr.getAppointmentId());
		cs.setNString(2, mr.getRecommendingDoctorFirstName());
		cs.setNString(3, mr.getRecommendingDoctorSecondName());
		cs.setNString(4, mr.getAssistentFirstName());
		cs.setNString(5, mr.getAssistentSecondName());
		cs.setNString(6, mr.getSimptoms());
		cs.setNString(7, mr.getDiagnosis());
		cs.setNString(8, mr.getRecommendations());
		
		String medicalServicesStatement = "CALL insert_medical_service_result(?, ?, ?)";
		CallableStatement cs2 = connection.prepareCall(medicalServicesStatement);
		
		for(Investigation i : mr.getServices())
		{
			cs2.setInt(1, i.getId());
			cs2.setInt(2, mr.getAppointmentId());
			cs2.setNString(3, i.getRawValue());
			
			cs2.execute();
		}
		
		cs.execute();
	}
}
