package com.revature.validate;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateImpl implements Validate{
	
	@Override
	public String validateUsername(String username) {
		String err = "";
		if (username.length() < 5) err += "\n \u001B[31m USERNAME: Username cannot be less than 5 characters. \u001B[0m";
		return  err;
	}

	@Override
	public String validatePassword(String pass) {
		String err = "";
		if (pass.length() < 5) err += "\n \u001B[31m PASSWORD: Password cannot be less than 5 characters. \u001B[0m";
		return  err;
	}

	@Override
	public String validateBirthday(String birth) {
		String err = "";
		String regex = "^((\\(\\d{3}\\))|\\d{2})[/]?\\d{2}[/]?\\d{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(birth);
		if (matcher.matches() == false) err+="\n \u001B[31m BIRTHDAY: Please type your phone number correctly \u001B[0m";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date d = null;
			try {
				d = sdf.parse(birth);
			} catch (ParseException e) {
				err += "\n \u001B[31m BIRTHDAY: Invalid birthday \u001B[0m";
			}
			
			LocalDate birthday=LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate curr=LocalDate.now();
			Period p=Period.between(birthday, curr);
			
			int age = p.getYears();;
			if (age < 17) err += "\n \u001B[31m BIRTHDAY: You are not old enough to register \u001B[0m";
			else if (age > 160) err += "\n \u001B[31m BIRTHDAY: Incorrect birthday. You cannot be " + age+" years old \u001B[0m";
			
		}
		return  err;
	}

	@Override
	public String validatePhone(String phone) {
		String err = "";
		String regex = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches() == false) err+="\n \u001B[31m PHONE: Please type your phone number correctly \u001B[0m";
		return  err;
	}

	@Override
	public String validateAddress(String addr) {
		String err = "";
		String regex = "^\\d{3,7}[ ]?[\\w ]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(addr);
		if (matcher.matches() == false) err+="\n \u001B[31m ADDRESS: Please type your address correctly (ex: 12394 Abc lane) \u001B[0m";
		return  err;
	}

	@Override
	public String validateZipCode(String zip) { 
		String err = "";
		try{
			  int z = Integer.parseInt(zip);
			} catch (NumberFormatException e) {
			  err += "\n \u001B[31m ZIPCODE: Please enter a number for Zipcode \u001B[0m";
			}
		if (zip.length() != 5) {
			System.out.println(zip.length());
			err += "\n \u001B[31m ZIPCODE: Please enter 5 digit number for Zipcode \u001B[0m";
		}
		return  err;
	}

	@Override
	public String validateState(String state) {
		String err = "";
		if (state.length() != 2) {
			err += "\n \u001B[31m STATE: Please type the State 2-letter code \u001B[0m";
		} else {
			List stateArr = new ArrayList<>(Arrays.asList("AL",
					"AK", "AS","AZ","AR","CA","CO","CT","DE","DC","FM","FL","GA",
					 "GU","HI","ID","IL","IN","IA","KS","KY","LA","ME","MH","MD","MA",
					 "MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND",
					 "MP","OH","OK","OR","PW","PA","PR","RI","SC","SD","TN","TX","UT",
					 "VT","VI","VA","WA","WV","WI","WY"));
			if (!stateArr.contains(state)) err += "\n \u001B[31m STATE: Incorrect State \u001B[0m";
		}
		return  err;
	}

}
