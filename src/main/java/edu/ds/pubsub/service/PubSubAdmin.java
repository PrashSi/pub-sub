package edu.ds.pubsub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import edu.ds.pubsub.publish.Publisher;
import edu.ds.pubsub.subscription.Subscriber;
import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.RoutingTable;

@Component
public class PubSubAdmin {

	// List to maintain all the topics.
	private HashMap<String, List<String>> topics;
	// routing table where Node Id will be the key.
	private HashMap<Integer, RoutingTable> routingTable;

	@Autowired
	private List<Subscriber> subscribers;

	@Autowired
	private Environment env;

	public PubSubAdmin() {

		// Initializing topics and respective subscriptions// global....
		topics = new HashMap<>();
		List<String> footballSubscriptions = new ArrayList<String>();
		footballSubscriptions.add("EPL-Node-1");
		footballSubscriptions.add("EPL-Node-2");
		footballSubscriptions.add("EPL-Node-3");
		topics.put("Football", footballSubscriptions);

		List<String> cricketSubscriptions = new ArrayList<String>();
		cricketSubscriptions.add("IPL-Node-1");
		cricketSubscriptions.add("IPL-Node-2");
		cricketSubscriptions.add("IPL-Node-3");
		topics.put("Cricket", cricketSubscriptions);

		// initializing routing table. Keeping IP and port hardcoded for demo purpose.
		// We are taking 3 nodes that will serve as the pub/sub system.

		routingTable = new HashMap<>();
		routingTable.put(1, new RoutingTable(8000, "machost", new HashSet<>()));
		routingTable.put(2, new RoutingTable(7000, "machost", new HashSet<>()));
		routingTable.put(3, new RoutingTable(8080, "machost", new HashSet<>()));

	}

	public void subscribe(String topic, String subscriptionId, Integer node) {

		if (this.routingTable.get(node).getSubscribers().contains(subscriptionId))
			return;
		// adding to routing table. Here I am maintaining all the subscriptions.
		this.routingTable.get(node).getSubscribers().add(subscriptionId);
		// send subscription to neighbours

		for (Integer key : routingTable.keySet()) {

			if (!key.equals(currentNode())) {
				// ignore current node.

				String url = "http://" + routingTable.get(key).getIp() + ":" + routingTable.get(key).getPort();
				try {
					Subscriber.subscribe(topic, subscriptionId, url, node);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void publish(String topic, Message message) {

		notify(match(topics.get(topic)), message);

		// publish to fwdlist and publish to neigbhours.
		if (message.getNode().equals(currentNode()))
			fwdList(topic, message);

	}

	/*
	 * returns the local subscriptions if any for the topic.
	 */
	public List<String> match(List<String> subscriptions) {

		List<String> copy = new ArrayList<>();
		Set<String> subscriber = routingTable.get(currentNode()).getSubscribers();
		for (String sub : subscriptions) {
			if (!subscriber.isEmpty() && subscriber.contains(sub))
				copy.add(sub);
		}
		return copy;
	}

	public void fwdList(String topic, Message message) {

		for (Integer key : routingTable.keySet()) {

			if (!key.equals(currentNode())) {
				// in production applications you need to identify only those nodes. Here we
				// know that both other nodes have subscriptions.

				String url = "http://" + routingTable.get(key).getIp() + ":" + routingTable.get(key).getPort();
				try {
					Publisher.publish(url, topic, message);
				} catch (Exception e) {
//					e.printStackTrace();
				}
			}
		}
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

	public Integer currentNode() {

		return Integer.parseInt(env.getProperty("node"));
	}
}
