package main.service.generator;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import main.domain.Order;
import main.domain.Prediction;

@Service
public class HealthFortuneGenerator implements FortuneGenerator{
	private static final String[] PREDICTION_TEXT = {"Ешь, молись, люби", 
													"У вас повреждение мозга. Вы обречены хорошо себя чувствовать до конца жизни",
													"Не выходите из себя. Неизвестно, что может произойти в ваше отсутствие",
													"Лучше быть здоровым, но богатым, чем бедным, но больным", 
													"Не верьте, если вам сказали, что вы больны, и тем более, если вам сказали, что вы здоровы", 
													"Сон лучше всякого лекарства", 
													"После десяти булочек жизнь кажется более полной"
};

	public Prediction generatePrediction(Order order) {
		Date date = order.getPredictionDate();
		String formattedDate = order.getPredictionDateFormatted();
		String name = order.getClientName();
		System.out.println("- О, духи, ответьте, какое будет здоровье у человека по имени " + name + " в день " + formattedDate + " ?");
		Random random = new Random(date.getTime() * name.chars().sum() * 2);
		String predictionText = PREDICTION_TEXT[random.nextInt(PREDICTION_TEXT.length)];
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		System.out.println("- Духи ответили, какое будет здоровье у " + name + ", в день " + formattedDate);
		return new Prediction(name, formattedDate, "Ваше предсказание по здоровью: " + predictionText);
	}
}
