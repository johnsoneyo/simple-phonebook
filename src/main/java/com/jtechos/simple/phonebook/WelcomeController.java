/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtechos.simple.phonebook;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author johnson3yo
 */
@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

}