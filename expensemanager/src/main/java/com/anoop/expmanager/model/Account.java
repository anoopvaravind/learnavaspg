package com.anoop.expmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private long id;
	private int month;
	private int year;
	private double openingBalance;
	private double clossingBalance;
	private double monthlyExpense;
	private double monthlyIncomm;
	private Date ceatedDate;
	private Date modifiedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getClossingBalance() {
		return clossingBalance;
	}

	public void setClossingBalance(double clossingBalance) {
		this.clossingBalance = clossingBalance;
	}

	public double getMonthlyExpense() {
		return monthlyExpense;
	}

	public void setMonthlyExpense(double monthlyExpense) {
		this.monthlyExpense = monthlyExpense;
	}

	public double getMonthlyIncomm() {
		return monthlyIncomm;
	}

	public void setMonthlyIncomm(double monthlyIncomm) {
		this.monthlyIncomm = monthlyIncomm;
	}

	public Date getCeatedDate() {
		return ceatedDate;
	}

	public void setCeatedDate(Date ceatedDate) {
		this.ceatedDate = ceatedDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
