package com.simplicia.commons;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {

	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);

	public static DataHelper getData() {
		return new DataHelper();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getFullName() {
		return faker.name().fullName();
	}

	public String getStreeetName() {
		return faker.address().streetName();
	}

	public String getCity() {
		return faker.address().city();
	}

	public String getState() {
		return faker.address().state();
	}

	public String getPin() {
		return faker.address().zipCode();
	}

	public String getPhone() {
		return faker.phoneNumber().cellPhone();
	}

	public int getNumber() {
		return faker.random().nextInt(10);
	}
}
