package edu.ds.pubsub.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@RequestMapping(value="/hello")
	public String helloWorld() {
		
		return "Hello World";
	}

}
