package com.java8.demo.model;

import java.time.LocalDate;
import java.util.List;

public class Customer {

	private String name;
	private List<Transaction> transactions;
	private LocalDate birthday;
	private State location;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public State getLocation() {
		return location;
	}

	public void setLocation(State location) {
		this.location = location;
	}
}
