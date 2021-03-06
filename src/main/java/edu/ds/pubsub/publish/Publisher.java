package edu.ds.pubsub.publish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import edu.ds.pubsub.type.Message;
import edu.ds.pubsub.type.UIMessage;

public abstract class Publisher {

	@Autowired(required = true)
	public Environment env;

	@Autowired(required = true)
	public RestTemplate restTemplate;

	public static void publish(String url, String topic, Message message) {

		RestTemplate rest = new RestTemplate();
		url = url.concat("/publish").concat("?topic=").concat(topic);
		rest.postForEntity(url, message, String.class);
	}

	public void publish(String topic, Message message) {

		String url = nearestNode().concat("/publish").concat("?topic=").concat(topic);
		restTemplate.postForEntity(url, message, String.class);
	}

	public String nearestNode() {

		return "http://localhost:" + env.getProperty("server.port");
	}

	public static List<UIMessage> messages = Collections.synchronizedList(new ArrayList<>());

}
