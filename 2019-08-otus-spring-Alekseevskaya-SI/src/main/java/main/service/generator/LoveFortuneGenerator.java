package main.service.generator;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import main.domain.Order;
import main.domain.Prediction;

@Service
public class LoveFortuneGenerator implements FortuneGenerator{

	private static final String[] PREDICTION_TEXT = {"Если Вы нашли человека своей мечты - с остальными мечтами уже можете распрощаться", 
													"В любви всегда один целует, а другой только подставляет щеку",
													"Впереди ждет любовное признание. Тайный поклонник проявит истинные чувства",
													"Вы станете для кого-то подарком судьбы", 
													"Любовь зла - полюбишь и козла", 
													"С глаз долой, из сердца вон", 
													"Где сердце лежит, туда и око бежит"
};
	public Prediction generatePrediction(Order order){
		Date date = order.getPredictionDate();
		String formattedDate = order.getPredictionDateFormatted();
		String name = order.getClientName();
		System.out.println("- О, духи, ответьте, что ждет в личной жизни человека по имени "+ name+ " в день " + formattedDate + " ?");
		Random random= new Random(date.getTime()* name.chars().sum() * 4);
		String predictionText = PREDICTION_TEXT[random.nextInt(PREDICTION_TEXT.length)];
		try {
			Thread.sleep(1000);
			}
			catch(Exception e) {}
		System.out.println("- Духи ответили, что ждет в личной жизни "+ name+ ", в день " + formattedDate);
        return new Prediction(name, formattedDate, "Духи любви говорят вам: " + predictionText);
    }
}
