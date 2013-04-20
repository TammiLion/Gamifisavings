package com.example.gamifisavings;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveGoal implements Serializable {

	private String name;
	private int amount;
	private long beginDate;
	private long endDate;
	private ArrayList<Payment> payments;
	
	public SaveGoal(String name) {
		this.name = name;
		amount = 0;
		beginDate = 0;
		endDate = 0;		
		payments = new ArrayList<Payment>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public long getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}
	
	public long getEndDate() {
		return endDate;
	}
	
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Payment> getPayments() {
		return payments;
	}
	
	public void addPayment(Payment payment) {
		payments.add(payment);
	}
	
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}
	
}
