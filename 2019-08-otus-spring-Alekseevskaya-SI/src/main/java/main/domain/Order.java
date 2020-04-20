package main.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	public static enum PredictionType{LOVE, BUSINESS, HEALTH};
	private final String clientName;
	private final Date predictionDate;
	private final PredictionType predictionType;
	
	public Order(String clientName, Date predictionDate, PredictionType predictionType) {
		this.clientName = clientName;
		this.predictionDate = predictionDate;
		this.predictionType = predictionType;
	}
	
	public String getClientName() {
		return clientName;
	}


	public Date getPredictionDate() {
		return predictionDate;
	}
	public String getPredictionDateFormatted() {
		return new SimpleDateFormat("dd-MM-yyyy").format(predictionDate);
	}

	public PredictionType getPredictionType() {
		return predictionType;
	}

	public String toString() {
		return clientName + " " + getPredictionDateFormatted() + " " + ":" + predictionType.toString();
	}
	
}
