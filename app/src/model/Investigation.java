package model;

import java.util.Vector;

public class Investigation {
	private int id;
	private String name;
	private int value = -1;
	private String rawValue;

	private int lowerBound = 0;
	private int upperBound = 0;
	
	private Vector<Investigation> selectableInvestigations = new Vector<Investigation>();
	
	public Investigation(int id, String name, int lowerBound, int upperBound) {
		this.id = id;
		this.name = name;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public Investigation(String name, int value, Vector<Investigation> selectableInvestigations) {
		this.name = name;
		this.value = value;
		this.selectableInvestigations = selectableInvestigations;
	}

	public Investigation(Vector<Investigation> selectableInvestigations) {
		this.selectableInvestigations = selectableInvestigations;
	}

	public Investigation(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}
	
	public String toString()
	{
		return name;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getRawValue() {
		return rawValue;
	}

	public void setRawValue(String value) {
		this.rawValue = value;
	}
	
	public Vector<Investigation> getSelectableInvestigations()
	{
		return selectableInvestigations;
	}

	public void setSelectableInvestigations(Vector<Investigation> selectableInvestigations) {
		this.selectableInvestigations = selectableInvestigations;
	}
	
	
	
}
