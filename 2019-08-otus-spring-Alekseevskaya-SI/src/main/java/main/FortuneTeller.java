package main;

import java.util.Collection;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import main.domain.Order;
import main.domain.Prediction;


@MessagingGateway
public interface FortuneTeller {
	@Gateway(requestChannel = "ordersChannel", replyChannel = "predictionsChannel")
    Collection<Prediction> process(Collection<Order> orders);
}
