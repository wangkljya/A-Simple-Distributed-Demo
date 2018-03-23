package com.wkl.object;

import java.io.Serializable;

public class Asset implements Serializable{
	private Integer userId;
	private String account;
	private Double currentCash;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Double getCurrentCash() {
		return currentCash;
	}
	public void setCurrentCash(Double currentCash) {
		this.currentCash = currentCash;
	}
}
