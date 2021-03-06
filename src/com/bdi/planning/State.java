package com.bdi.planning;

import java.io.Serializable;
import java.sql.Timestamp;

public class State implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Name;
	private String Content;
	private String Result;
	private int Is_initial;
	private int Is_goal;
	private String Plan_state;
	private Timestamp Checkpoint_date;
	private int totalResult;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getIs_initial() {
		return Is_initial;
	}
	public void setIs_initial(int is_initial) {
		Is_initial = is_initial;
	}
	public int getIs_goal() {
		return Is_goal;
	}
	public void setIs_goal(int is_goal) {
		Is_goal = is_goal;
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
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getPlan_state() {
		return Plan_state;
	}
	public void setPlan_state(String plan_state) {
		Plan_state = plan_state;
	}
	public Timestamp getCheckpoint_date() {
		return Checkpoint_date;
	}
	public void setCheckpoint_date(Timestamp checkpoint_date) {
		Checkpoint_date = checkpoint_date;
	}

}
