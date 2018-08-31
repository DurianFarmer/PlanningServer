package com.bdi.planning;
import java.io.Serializable;
import java.sql.Timestamp;

public class Plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int parentId;
	private String name;
	private int projectId;
	private int versionId;
	private Timestamp date_created;
	private int user_create;
	private int totalResult;
	private String project_name;
	private String user_create_name;
	private long initial_budget;
	private long executed_budget;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public int getUser_create() {
		return user_create;
	}
	public void setUser_create(int user_create) {
		this.user_create = user_create;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getUser_create_name() {
		return user_create_name;
	}
	public void setUser_create_name(String user_create_name) {
		this.user_create_name = user_create_name;
	}
	public long getInitial_budget() {
		return initial_budget;
	}
	public void setInitial_budget(long initial_budget) {
		this.initial_budget = initial_budget;
	}
	public long getExecuted_budget() {
		return executed_budget;
	}
	public void setExecuted_budget(long executed_budget) {
		this.executed_budget = executed_budget;
	}

}
