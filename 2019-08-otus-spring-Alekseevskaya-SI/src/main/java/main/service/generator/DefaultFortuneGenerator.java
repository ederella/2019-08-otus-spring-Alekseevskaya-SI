package main.service.generator;

import org.springframework.stereotype.Service;

import main.domain.Order;
import main.domain.Prediction;
@Service
public class DefaultFortuneGenerator implements FortuneGenerator{
	private static final String[] PREDICTION_TEXT = {"Будущее туманно"};

public Prediction generatePrediction(Order order) {	
	return new Prediction(order.getClientName(), order.getPredictionDate().toString(), PREDICTION_TEXT[0]);
}
}
