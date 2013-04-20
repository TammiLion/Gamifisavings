package com.example.gamifisavings;

import java.util.Date;

public class Payment {

	private int amount;
	private Date date;
	boolean isPlanned; //Whether the payment is planned by the user.
	boolean isDone;
	
	public Payment(int amount, Date date, boolean isPlanned) {
		this.amount = amount;
		this.date = date;
		this.isPlanned = isPlanned;
		isDone=this.isPlanned?false:true; //If the payment is planned it is not done yet on creation else it must be a recent deposit.
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Date getDate() {
		return date;
	}
	
	public boolean isPlanned() {
		return isPlanned;
	}
	
	public boolean isDone() {
		return isDone;
	}
}
