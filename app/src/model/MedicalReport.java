package model;

import java.time.LocalDate;
import java.util.Vector;

public class MedicalReport {
	private String patientFirstName, patientSecondName;
	private String doctorFirstName, doctorSecondName;
	private String recommendingDoctorFirstName, recommendingDoctorSecondName;
	private String assistentFirstName, assistentSecondName;
	private String simptoms, diagnosis, recommendations;
	private int appointmentId;
	
	private Vector<Investigation> services;
	
	private LocalDate date;

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientSecondName() {
		return patientSecondName;
	}

	public void setPatientSecondName(String patientSecondName) {
		this.patientSecondName = patientSecondName;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}

	public String getDoctorSecondName() {
		return doctorSecondName;
	}

	public void setDoctorSecondName(String doctorSecondName) {
		this.doctorSecondName = doctorSecondName;
	}

	public String getRecommendingDoctorFirstName() {
		return recommendingDoctorFirstName;
	}

	public void setRecommendingDoctorFirstName(String recommendingDoctorFirstName) {
		this.recommendingDoctorFirstName = recommendingDoctorFirstName;
	}

	public String getRecommendingDoctorSecondName() {
		return recommendingDoctorSecondName;
	}

	public void setRecommendingDoctorSecondName(String recommendingDoctorSecondName) {
		this.recommendingDoctorSecondName = recommendingDoctorSecondName;
	}

	public String getAssistentFirstName() {
		return assistentFirstName;
	}

	public void setAssistentFirstName(String assistentFirstName) {
		this.assistentFirstName = assistentFirstName;
	}

	public String getAssistentSecondName() {
		return assistentSecondName;
	}

	public void setAssistentSecondName(String assistentSecondName) {
		this.assistentSecondName = assistentSecondName;
	}

	public String getSimptoms() {
		return simptoms;
	}

	public void setSimptoms(String simptoms) {
		this.simptoms = simptoms;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public Vector<Investigation> getServices() {
		return services;
	}

	public void setServices(Vector<Investigation> investigations) {
		this.services = investigations;
	}
	
	public int getAppointmentId()
	{
		return appointmentId;
	}
	
	public void setAppointmentId(int appointmentId)
	{
		this.appointmentId = appointmentId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

}
