package com.bdi.planning;

import java.io.Serializable;
import java.sql.Timestamp;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int goalId;
	private String name;
	private String description;
	private Timestamp date_start;
	private Timestamp date_end;
	private Timestamp date_created;
	private int manager_id;
	private String manager_name;
	private int totalResult;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate_start() {
		return date_start;
	}
	public void setDate_start(Timestamp date_start) {
		this.date_start = date_start;
	}
	public Timestamp getDate_end() {
		return date_end;
	}
	public void setDate_end(Timestamp date_end) {
		this.date_end = date_end;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

}
