package main.service.generator;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import main.domain.Order;
import main.domain.Prediction;

@Service
public class BusinessFortuneGenerator implements FortuneGenerator{
	
	private static final String[] PREDICTION_TEXT = {"Винограднику нужна не молитва, а мотыга", 
													"Куй железо, пока горячо",
													"Шаг через пропасть - спасение, полшага - гибель",
													"Молчание - золото", 
													"Тише едешь - дальше будешь", 
													"Делай, что должен, и свершится, чему суждено", 
													"Под лежачий камень вода не течет"
	};
	
	public Prediction generatePrediction(Order order) {
		Date date = order.getPredictionDate();
		String formattedDate = order.getPredictionDateFormatted();
		String name = order.getClientName();
		System.out.println("- О, духи, ответьте, что ждет в бизнесе " + name + " в день " + formattedDate + " ?");
		Random random = new Random(date.getTime() * name.chars().sum() * 2);
		String predictionText = PREDICTION_TEXT[random.nextInt(PREDICTION_TEXT.length)];
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		System.out.println("- Духи ответили, что ждет в бизнесе человека по имени " + name + ", в день " + formattedDate);
		return new Prediction(name, formattedDate, "Духи богатства ответили на ваш запрос: " + predictionText);
	}
}
