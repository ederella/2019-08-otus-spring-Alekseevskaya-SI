package main.shell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import main.domain.Order;
import main.domain.Order.PredictionType;

public class OrdersCreator {
	
	public static Collection<Order> generateOrders() {
		 List<Order> orders = new ArrayList<>();
	        for (int i = 0; i < RandomUtils.nextInt(1, 7); ++i) {
	        	orders.add(generateOrder());
	        }
	        System.out.println(orders.toString());
	        return orders;
	}
	public static Collection<Order> generateOrderSpecial(String name, String dateString, String type) throws Exception {
		Date date;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
		} catch (ParseException e) {
			System.out.println("Incorrect date,  we took current date");
			date = new Date();
		}  
		 PredictionType t;
		 if(type.matches("L|l|love|Love|LOVE")) {
			 t = PredictionType.LOVE;
		 }
		 else if(type.matches("H|h|health|Health|HEALTH")) {
			 t = PredictionType.HEALTH;
		 }
		 else if(type.matches("B|b|business|Business|BUSINESS")) {
			 t = PredictionType.BUSINESS;
		 }
		 else {
			 throw new Exception("We have no predictions for type"+ type);
		 }
				 
		 List<Order> orders = new ArrayList<>();	       
	      orders.add(new Order(name, date, t));
	      return orders;
	}
	private static Order generateOrder() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		int predictionTypeInt = RandomUtils.nextInt(1,4);
		PredictionType t = predictionTypeInt == 1 ? PredictionType.LOVE
							: predictionTypeInt == 2 ? PredictionType.BUSINESS 
							: PredictionType.HEALTH;
		return new Order(generatedString, new Date(), t);
	}
	
}
