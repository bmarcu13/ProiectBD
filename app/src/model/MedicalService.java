package model;

import java.time.LocalTime;

public class MedicalService {
	private int id;
	private String name;
	private int price;
	private LocalTime duration;
	
	public MedicalService(int _id, String _name, LocalTime _duration, int _price)
	{
		this.id = _id;
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
	
	public int getId()
	{
		return id;
	}
	
	public String toString()
	{
		return name;
	}
	
}
