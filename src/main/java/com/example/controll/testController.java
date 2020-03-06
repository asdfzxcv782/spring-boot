package com.example.controll;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;


@Controller
/*RestController only return json*/
public class testController {
	@GetMapping(value = "/")
	public String root(){
		System.out.printf("greetings");
		return "vue";
	}
	@GetMapping(value = "/main")
	public String test(Authentication authentication,Model model){
		model.addAttribute("name", authentication.getName());
		System.out.printf("main");
		return "vue";
	}
	@GetMapping(value = "/hello")
	public String hello(@RequestParam(value="title", required=false, defaultValue="Lees") String title, Model model) {
        model.addAttribute("name", title);
        return "index";
    }
	/*@GetMapping(value = "/os")
	public String os(@RequestHeader(value = "User-Agent") String myAgent,
			@RequestHeader(value = "Host") String myHost, Authentication authentication,Model model) {
		model.addAttribute("name", myAgent);
		model.addAttribute("host", myHost);
		System.out.printf(myHost);
		System.out.printf(authentication.getName());
		return "practice";
	}*/
	@GetMapping(value = "/vue")
	public String vue(){
		return "vue";
	}
}