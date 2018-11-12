package edu.ds.pubsub.subscription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

public abstract class Subscriber {

	@Autowired(required = true)
	public Environment env;

	@Autowired(required = true)
	private RestTemplate rest;

	public abstract void receiveMessage(Message message);

	public abstract String getSubscriptionId();

	public static void subscribe(String topic, String subscriptionId, String ip, Integer node) {

		RestTemplate rest = new RestTemplate();
		String url = ip + "/subscribe?topic=" + topic + "&subscriptionId=" + subscriptionId + "&node=" + node;
		rest.getForEntity(url, String.class);
	}

	public void subscribe(String topic, String subscriptionId, Integer node) {

		String url = nearestNode() + "/subscribe?topic=" + topic + "&subscriptionId=" + subscriptionId + "&node="
				+ node;

		rest.getForEntity(url, String.class);
	}

	public String nearestNode() {

		return "http://localhost:" + env.getProperty("server.port");
	}

	public List<UIMessage> messages = new ArrayList<>();

}
