package com.bdi.planning;

import java.io.Serializable;

public class Constraint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int Plan_id;
	private String Constraint_rule;
	private int totalResult;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getPlan_id() {
		return Plan_id;
	}
	public void setPlan_id(int plan_id) {
		Plan_id = plan_id;
	}
	public String getConstraint_rule() {
		return Constraint_rule;
	}
	public void setConstraint_rule(String constraint_rule) {
		Constraint_rule = constraint_rule;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

}
