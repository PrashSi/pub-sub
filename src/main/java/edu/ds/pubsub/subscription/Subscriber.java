package edu.ds.pubsub.subscription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import edu.ds.pubsub.service.PubSubAdmin;
import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

public abstract class Subscriber {

	@Autowired(required = true)
	public Environment env;

	@Autowired(required = true)
	private RestTemplate rest;

	@Autowired
	private PubSubAdmin admin;

	public abstract void receiveMessage(Message message);

	public abstract String getSubscriptionId();

	public void subscribe(String topic, String subscriptionId) {
//
//		String url = nearestNode().concat("/subscribe") + "?topic=" + topic + "&subscriptionId=" + subscriptionId;
//
////		HashMap<String, String> params = new HashMap<>();
////		params.put("topic", topic);
////		params.put("subscriptionId", subscriptionId);
//		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		admin.subscribe(topic, subscriptionId);
	}

	public String nearestNode() {

		return "http://localhost:" + env.getProperty("server.port");
	}

	public List<UIMessage> messages = new ArrayList<>();

}
