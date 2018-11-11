package edu.ds.pubsub.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.subscription.Subscriber;
import edu.ds.pubsub.type.Message;

@Component
public class PubSubAdmin {

	// List to maintain all the topics.
	private HashMap<String, List<String>> topics;

	@Autowired
	private List<Subscriber> subscribers;

	public PubSubAdmin() {

		topics = new HashMap<>();
		topics.put("Football", new ArrayList<>());
		topics.put("Cricket", new ArrayList<>());
	}

	public void subscribe(String topic, String subscriptionId) {

		this.topics.get(topic).add(subscriptionId);
	}

	public void publish(String topic, Message message) {

		List<String> subscriptions = match(topic);
		notify(subscriptions, message);

	}

	public List<String> match(String topic) {

		// for phase 2 all the subscription will of this node only so need of filtering.
		return topics.get(topic);
	}

	public void notify(List<String> subscriptions, Message message) {
		// notify the subscribed subscribers only.
		for (String sub : subscriptions) {
			Optional<Subscriber> subscriber = subscribers.stream()
					.filter(s -> s.getSubscriptionId().equalsIgnoreCase(sub)).findFirst();

			if (subscriber.isPresent()) {
				subscriber.get().receiveMessage(message);
			}
		}
	}
}
