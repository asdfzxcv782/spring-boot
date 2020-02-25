package com.example.controll;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;


@Controller  
/*RestController only return json*/
public class testController {
	@GetMapping(value = "/greeting")
	public String greetings(){
		System.out.printf("greeting");
		return "你好啊，Spring Boot";
	}
	@GetMapping(value = "/hello")
	public String hello(@RequestParam(value="title", required=false, defaultValue="Lees") String title, Model model) {
        model.addAttribute("name", title);
        return "index";
    }
	@GetMapping(value = "/os")
	public String os(@RequestHeader(value = "User-Agent") String myAgent,
			@RequestHeader(value = "Host") String myHost, Model model) {
		model.addAttribute("name", myAgent);
		model.addAttribute("host", myHost);
		System.out.printf(myHost);
		return "practice";
	}
	@GetMapping(value = "/vue")
	public String vue(){
		return "vue";
	}
}