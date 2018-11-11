package edu.ds.pubsub.subscription;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

/*
 * SUBSCRIBER. 
 */
@Component
public class IndianPremierLeague extends Subscriber {
	// @Scheduled(initialDelay = 4000)
	@PostConstruct
	public void subscribe() {
		super.subscribe("Cricket", "IPL");
	}

	/*
	 * Pub/sub system calls this function to notify subscriber
	 * 
	 * @see edu.ds.pubsub.subscription.Subscriber#receiveMessage(edu.ds.pubsub.type.
	 * Message)
	 */
	@Override
	public void receiveMessage(Message message) {
		super.messages.add(new UIMessage("Received by IPL: " + message.toString()));
		System.out.println("Message received by IPL: " + message.toString());
	}

	@Override
	public String getSubscriptionId() {
		return "IPL";
	}
}
