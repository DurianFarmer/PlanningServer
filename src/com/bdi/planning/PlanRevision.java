package com.bdi.planning;
import java.io.Serializable;
import java.sql.Timestamp;

public class PlanRevision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int planId;
	private Timestamp date_created;
	private Timestamp date_updated;
	private int user_created;
	private int user_updated;
	private String comment;
	private String user_created_name;
	private String user_updated_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Timestamp getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Timestamp date_updated) {
		this.date_updated = date_updated;
	}
	public int getUser_created() {
		return user_created;
	}
	public void setUser_created(int user_created) {
		this.user_created = user_created;
	}
	public int getUser_updated() {
		return user_updated;
	}
	public void setUser_updated(int user_updated) {
		this.user_updated = user_updated;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUser_created_name() {
		return user_created_name;
	}
	public void setUser_created_name(String user_created_name) {
		this.user_created_name = user_created_name;
	}
	public String getUser_updated_name() {
		return user_updated_name;
	}
	public void setUser_updated_name(String user_updated_name) {
		this.user_updated_name = user_updated_name;
	}

}
