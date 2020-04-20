package main.shell;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import main.FortuneTeller;
import main.domain.Order;
import main.domain.Prediction;

@ShellComponent
public class ShellCommands {

	private final FortuneTeller fortuneTeller;
	
	@Autowired
	public ShellCommands(FortuneTeller ft) {
		this.fortuneTeller = ft;
	}
	
	@ShellMethod(value = "run predictions auto generation", key = "auto")
	public void runAutoGeneration(int iterationsNumber) {
		for (int i = 0; i < iterationsNumber; i++) {
			Collection<Order> items = OrdersCreator.generateOrders();
	        Collection<Prediction> predictions = fortuneTeller.process(items);
	        predictions.forEach(prediction -> System.out.println(prediction.getText()));
		}
		
	}
	
	@ShellMethod(value = "generate individual prediction", key = "me")
	public void generateIndividualPrediction(String name, String date, String type) {
		try {
			Collection<Order> items = OrdersCreator.generateOrderSpecial(name, date, type);
			Collection<Prediction> predictions = fortuneTeller.process(items);
			predictions.forEach(prediction -> System.out.println(prediction.getText()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
