package model;

import java.time.LocalTime;

public class Appointment {
	private int id;
	private String patientFirstName;
	private String patientSecondName;
	private LocalTime time;
	private boolean isRegistered = false;
	
	public Appointment(int id, String patientFirstName, String patientSecondName, LocalTime time) {
		this.id = id;
		this.patientFirstName = patientFirstName;
		this.patientSecondName = patientSecondName;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public String getPatientSecondName() {
		return patientSecondName;
	}

	public LocalTime getTime() {
		return time;
	}
	
	public boolean getIsRegistered()
	{
		return isRegistered;
	}
	
	public void register()
	{
		isRegistered = true;
	}
	
}
