package com.bdi.planning;
import java.io.Serializable;
import java.sql.Timestamp;

public class PlanDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int planId;
	private int itemId;
	private String item_value;
	private int user_assigned;
	private Timestamp date_start;
	private Timestamp date_end;
	private int cur_version;
	private int totalResult;
	private String item_name;
	private String item_unit;
	private String user_assigned_name;
	private long actual_value;
	
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
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItem_value() {
		return item_value;
	}
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	public int getUser_assigned() {
		return user_assigned;
	}
	public void setUser_assigned(int user_assigned) {
		this.user_assigned = user_assigned;
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
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getUser_assigned_name() {
		return user_assigned_name;
	}
	public void setUser_assigned_name(String user_assigned_name) {
		this.user_assigned_name = user_assigned_name;
	}
	public String getItem_unit() {
		return item_unit;
	}
	public void setItem_unit(String item_unit) {
		this.item_unit = item_unit;
	}
	public int getCur_version() {
		return cur_version;
	}
	public void setCur_version(int cur_version) {
		this.cur_version = cur_version;
	}
	public long getActual_value() {
		return actual_value;
	}
	public void setActual_value(long actual_value) {
		this.actual_value = actual_value;
	}

}
