package edu.ds.pubsub.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

/*
 * SUBSCRIBER. 
 */
@Component
public class IndianPremierLeague extends Subscriber {

	@Autowired
	private Environment env;

	public void subscribe() {
		super.subscribe("Cricket", getSubscriptionId(), Integer.parseInt(env.getProperty("node")));
	}

	/*
	 * Pub/sub system calls this function to notify subscriber
	 * 
	 * @see edu.ds.pubsub.subscription.Subscriber#receiveMessage(edu.ds.pubsub.type.
	 * Message)
	 */
	@Override
	public void receiveMessage(Message message) {
		super.messages.add(new UIMessage(message.toString()));
		System.out.println("Received by IPL: " + message.toString());
	}

	@Override
	public String getSubscriptionId() {
		return "IPL-Node-" + env.getProperty("node");
	}
}
