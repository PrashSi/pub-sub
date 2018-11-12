package edu.ds.pubsub.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ds.pubsub.service.CompilerService;

@RestController
public class CompilerRestController {

	@Autowired
	private CompilerService compilerService;

	@RequestMapping("/run-code")
	public Map<String, String> compile(@RequestParam String code) throws IOException, InterruptedException {

		Map<String, String> response = new HashMap<>();
		response.put("output", this.compilerService.compile(code));
		return response;
	}
}
