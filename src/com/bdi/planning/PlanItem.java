package com.bdi.planning;

import java.io.Serializable;

public class PlanItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int ParentId;
	private String Name;
	private String Description;
	private String Unit;
	private int totalResult;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public void setId(int id) {
		Id = id;
	}

}
