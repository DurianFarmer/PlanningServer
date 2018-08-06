package com.bdi.planning;

import java.io.Serializable;
import java.sql.Timestamp;

public class Goal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int ParentId;
	private String Target;
	private String Description;
	private Timestamp Date_created;
	private int User_created;
	private String Username;
	private int totalResult;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getParentId() {
		return ParentId;
	}
	public void setParentId(Integer parentId) {
		ParentId = parentId;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Timestamp getDate_created() {
		return Date_created;
	}
	public void setDate_created(Timestamp date_created) {
		Date_created = date_created;
	}
	public Integer getUser_created() {
		return User_created;
	}
	public void setUser_created(Integer user_created) {
		User_created = user_created;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

}
