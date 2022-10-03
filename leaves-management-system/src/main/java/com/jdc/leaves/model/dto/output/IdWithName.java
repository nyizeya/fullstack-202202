package com.jdc.leaves.model.dto.output;

public class IdWithName {

	private int id;
	private String name;
	
	public IdWithName() {}

	public IdWithName(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
