package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;

import main.domain.Order;
import main.domain.Order.PredictionType;

@IntegrationComponentScan
@EnableIntegration
@Configuration
@ComponentScan
public class Config {

	@Bean
    public QueueChannel ordersChannel() {
        return MessageChannels.queue(7).get();
    }
	
	@Bean
    public PublishSubscribeChannel predictionsChannel() {
		 return MessageChannels.publishSubscribe().get();
    }
    @Bean (name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get() ;
    }
	@Bean
	public IntegrationFlow flow() {
	     return IntegrationFlows.from("ordersChannel")
	                .split()
	                .<Order, String> route (order -> order.getPredictionType().toString(), 
	                						mapping -> mapping
	                								 .subFlowMapping(PredictionType.BUSINESS.toString(), 
	                										sf -> sf.handle("businessFortuneGenerator", "generatePrediction"))
	                								 .subFlowMapping(PredictionType.HEALTH.toString(), 
	    	                								sf -> sf.handle("healthFortuneGenerator", "generatePrediction"))
	                								 .subFlowMapping(PredictionType.LOVE.toString(), 
	                										sf -> sf.handle("loveFortuneGenerator", "generatePrediction"))
	                								 .defaultSubFlowMapping(
	                										sf -> sf.handle("defaultFortuneGenerator", "generatePrediction"))
	                						)
	                .aggregate()
	                .transform(Transformers.toJson())
	                .handle("fileServices", "save")
					.transform(Transformers.fromJson())
	                .channel("predictionsChannel")
	                .get();
	    }
}
