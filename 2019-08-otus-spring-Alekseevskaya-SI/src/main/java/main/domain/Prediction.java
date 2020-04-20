package main.domain;


public class Prediction {
	
	private final String clientName;
	private final String predictionDate;
	private final String text;
	
	public Prediction() {
		this.clientName = "";
		this.predictionDate = "";
		this.text = "";
	}	
	public Prediction(String clientName, String predictionDate, String text) {
		this.clientName = clientName;
		this.predictionDate = predictionDate;
		this.text = text;
	}	
	public String getClientName() {
		return clientName;
	}
	public String getPredictionDate() {
		return predictionDate;
	}
	public String getText() {
		return text;
	}
	
}
