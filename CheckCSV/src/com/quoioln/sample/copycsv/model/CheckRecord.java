package com.quoioln.sample.copycsv.model;

public class CheckRecord {
	private String message;
	
	private boolean success;
	
	
	
	/**
	 * The constructor
	 * @param message
	 * @param success
	 */
	public CheckRecord(String message, boolean success) {
		this.message = message;
		this.success = success;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
