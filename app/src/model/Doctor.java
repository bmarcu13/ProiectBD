package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Doctor {
	private String firstName;
	private String lastName;
	private String cnp;
	private Vector<MedicalService> services;
	
	public Doctor(String _cnp, String _firstName, String _lastName, Vector<MedicalService> _services)
	{
		cnp = _cnp;
		firstName = _firstName;
		lastName = _lastName;
		services = _services;
	}
	
	public Doctor(String _cnp, String _firstName, String _lastName)
	{
		cnp = _cnp;
		firstName = _firstName;
		lastName = _lastName;
	}
	
	
	public String toString()
	{
		return firstName + " " + lastName;
	}
	
	public Vector<MedicalService> getServices()
	{
		return this.services;
	}
	
	public void setServices(Vector<MedicalService> _services)
	{
		this.services = _services;
	}
	
	public String getCNP()
	{
		return this.cnp;
	}
}
