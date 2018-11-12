package edu.ds.pubsub.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

@Component
public class PremierLeague extends Subscriber {

	@Autowired
	private Environment env;

	public void subscribe() {
		super.subscribe("Football", getSubscriptionId(), Integer.parseInt(env.getProperty("node")));
	}

	@Override
	public void receiveMessage(Message message) {
		super.messages.add(new UIMessage(message.toString()));
	}

	@Override
	public String getSubscriptionId() {
		return "EPL-Node-" + env.getProperty("node");
	}
}
