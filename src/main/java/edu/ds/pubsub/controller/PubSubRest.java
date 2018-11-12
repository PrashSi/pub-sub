package edu.ds.pubsub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ds.pubsub.service.PubSubAdmin;
import edu.ds.pubsub.type.Message;

@RestController
public class PubSubRest {

	@Autowired
	private PubSubAdmin admin;

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String subscribe(@RequestParam String topic, @RequestParam String subscriptionId , @RequestParam Integer  node) {

		admin.subscribe(topic, subscriptionId, node);
		return "Success";
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publish(@RequestParam String topic, @RequestBody Message message) {

		admin.publish(topic, message);
		return "Success";
	}
}
