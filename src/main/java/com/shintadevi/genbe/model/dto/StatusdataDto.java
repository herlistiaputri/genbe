package com.shintadevi.genbe.model.dto;


public class StatusdataDto {
	private String status;
	private String message;
	private DatalengkapDto data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DatalengkapDto getData() {
		return data;
	}
	public void setData(DatalengkapDto data) {
		this.data = data;
	}
	
	
}