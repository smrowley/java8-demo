package com.java8.demo.model;

public enum Category {
	FOOD,GAS,TRAVEL,APPAREL,ENTERTAINMENT;
	
	public static Category getRandom() {
		return Category.values()[(int) Math.random() * Category.values().length];
	}
}
