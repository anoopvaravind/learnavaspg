package com.anoop.expmanager.DO;

public class UserIncommExpenseSummaryDO {
	int id;
	long userID;
	double expensePaid;
	double due;
	double rentPaid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public double getExpensePaid() {
		return expensePaid;
	}

	public void setExpensePaid(double expensePaid) {
		this.expensePaid = expensePaid;
	}

	public double getRentPaid() {
		return rentPaid;
	}

	public void setRentPaid(double rentPaid) {
		this.rentPaid = rentPaid;
	}

	public double getDue() {
		return due;
	}

	public void setDue(double due) {
		this.due = due;
	}

}
