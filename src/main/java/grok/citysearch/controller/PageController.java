package grok.citysearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

	@RequestMapping("/")
	public String index(){
		return "Hello world";
	}
}
