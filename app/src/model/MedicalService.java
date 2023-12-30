package model;

import java.time.LocalTime;

public class MedicalService {
	private String name;
	private int price;
	private LocalTime duration;
	
	public MedicalService(String _name, LocalTime _duration, int _price)
	{
		this.name = _name;
		this.duration = _duration;
		this.price = _price;
	}
	
	public String getName()
	{
		return name;
	}
	
	public LocalTime getDuration()
	{
		return duration;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public String toString()
	{
		return name;
	}
	
}
