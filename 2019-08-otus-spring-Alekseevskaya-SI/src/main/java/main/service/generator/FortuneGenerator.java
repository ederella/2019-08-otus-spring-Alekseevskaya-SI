package main.service.generator;

import main.domain.Order;
import main.domain.Prediction;

public interface FortuneGenerator {
	public Prediction generatePrediction(Order order);
}
