package edu.ds.pubsub.publish;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

@Component
public class Cricket extends Publisher {

	int count = 0;

	// publish message to the nearest node. In this case it is only one.
	@Scheduled(initialDelay = 5000, fixedRate = 15000)
	public void publish() {
		count++;
		System.out.println("Published by Cricket fans... " + count);
		Message message = new Message(count, "Published by Cricket fans...");
		messages.add(new UIMessage("On topic: Cricket, with Id:" + count));
		super.publish("Cricket", message);
	}
}
