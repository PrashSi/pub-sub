package edu.ds.pubsub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.ds.pubsub.type.Code;

@Controller
public class CompilerController {

	@GetMapping("/compiler")
	public String compiler() {

		return "compiler";
	}

}
