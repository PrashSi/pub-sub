package edu.ds.pubsub.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

@Component
public class Cricket extends Publisher {

	@Autowired
	private Environment env;

	int count = 0;

	// publish message to the nearest node. In this case it is only one.
	@Scheduled(initialDelay = 6000, fixedRate = 15000)
	public void publish() {
		count++;
		System.out.println("Published by Cricket fans... " + count);
		Message message = new Message(count, Integer.parseInt(env.getProperty("node")), "Published by Cricket fans...");
		messages.add(new UIMessage("On topic: Cricket, with Id:" + count));
		super.publish("Cricket", message);
	}
}
