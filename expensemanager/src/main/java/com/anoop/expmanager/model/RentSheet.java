package com.anoop.expmanager.model;

import com.anoop.expmanager.util.CustomDateFormatter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rentsheet")
public class RentSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RENTSHEET_ID")
	private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
	private User user;
	private double originalRentAmount;
	private double totalExpensePaid;
	private double adjustedRent;
	private int rentGeneratedForMonth;
	private int rentGeneratedForYear;
	private Date rentGeneratedDate;
	private double due;
    private double advancePayment;
	private double rentActullyPaid;
	private Date rentPaidDate;
	private Date modifiedDate;
	private String comment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getOriginalRentAmount() {
		return originalRentAmount;
	}

	public void setOriginalRentAmount(double originalRentAmount) {
		this.originalRentAmount = originalRentAmount;
	}

	public double getTotalExpensePaid() {
		return totalExpensePaid;
	}

	public void setTotalExpensePaid(double totalExpensePaid) {
		this.totalExpensePaid = totalExpensePaid;
	}

	public double getAdjustedRent() {
		return adjustedRent;
	}

	public void setAdjustedRent(double adjustedRent) {
		this.adjustedRent = adjustedRent;
	}

	public int getRentGeneratedForMonth() {
		return rentGeneratedForMonth;
	}

	public void setRentGeneratedForMonth(int rentGeneratedForMonth) {
		this.rentGeneratedForMonth = rentGeneratedForMonth;
	}

	public int getRentGeneratedForYear() {
		return rentGeneratedForYear;
	}

	public void setRentGeneratedForYear(int rentGeneratedForYear) {
		this.rentGeneratedForYear = rentGeneratedForYear;
	}

    @JsonSerialize(using = CustomDateFormatter.class)
    public Date getRentGeneratedDate() {
		return rentGeneratedDate;
	}

	public void setRentGeneratedDate(Date rentGeneratedDate) {
		this.rentGeneratedDate = rentGeneratedDate;
	}

	public double getDue() {
		return due;
	}

	public void setDue(double due) {
		this.due = due;
	}

	public double getRentActullyPaid() {
		return rentActullyPaid;
	}

	public void setRentActullyPaid(double rentActullyPaid) {
		this.rentActullyPaid = rentActullyPaid;
	}

    @JsonSerialize(using = CustomDateFormatter.class)
    public Date getRentPaidDate() {
		return rentPaidDate;
	}

	public void setRentPaidDate(Date rentPaidDate) {
		this.rentPaidDate = rentPaidDate;
	}

    @JsonSerialize(using = CustomDateFormatter.class)
    public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    public double getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(double advancePayment) {
        this.advancePayment = advancePayment;
    }
}
