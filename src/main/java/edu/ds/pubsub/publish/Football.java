package edu.ds.pubsub.publish;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

@Component
public class Football extends Publisher {

	int count = 0;

	@Scheduled(initialDelay = 5000, fixedRate = 10000)
	public void publish() {
		count++;
		System.out.println("Publishing message " + count);
		Message message = new Message(count, Integer.parseInt(env.getProperty("node")),
				"Published by Football fans...");
		messages.add(new UIMessage("On topic: Football, with Id:" + count));
		super.publish("Football", message);
	}
}
