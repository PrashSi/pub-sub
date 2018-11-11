package edu.ds.pubsub.subscription;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

@Component
public class PremierLeague extends Subscriber {

//	@Scheduled(initialDelay = 4000)
	@PostConstruct
	public void subscribe() {
		super.subscribe("Football", "PremierLeague");
	}

	@Override
	public void receiveMessage(Message message) {
		super.messages.add(new UIMessage("Received by EPL: " + message.toString()));
		System.out.println("Message received: " + message.toString());
	}

	@Override
	public String getSubscriptionId() {
		return "PremierLeague";
	}
}
