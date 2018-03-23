package com.wkl.object;

import java.io.Serializable;
import java.util.Date;

public class GoodOrder implements Serializable{
	private Long orderId;
	private Integer productId;
	private String productName;
	private Integer userId;
	private String userName;
	private Double balance;
	private Date time;
	
	public GoodOrder(){
		
	}
	
	public GoodOrder(Long orderId, Integer productId, String productName,
			Integer userId, String userName, Double balance, Date time) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.userId = userId;
		this.userName = userName;
		this.balance = balance;
		this.time = time;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
