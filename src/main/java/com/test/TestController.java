package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

	private static final String secretKey = "6LebY3QUAAAAAO6wD4eEbsWqyJGLzONBRT0FS_a1";
	
	@GetMapping("/")
	public String test() {
		return "test";
	}
	
	@GetMapping("/recaptcha")
	public ModelAndView recaptcha() {
		ModelAndView mv = new ModelAndView("recaptcha");
		mv.addObject("sitekey", "6LebY3QUAAAAAPQ2hHWb3edUk9dSZXZffBNHua0a");
		mv.addObject("secretkey", "6LebY3QUAAAAAO6wD4eEbsWqyJGLzONBRT0FS_a1");
		
		//mv.setViewName("/recaptcha");
		return mv;
	}	
	
	@GetMapping("/verify")
	public boolean verify(@RequestParam("token") String token){
		return isCaptchaValid(token);
	}
	
	/** 
	* Validates Google reCAPTCHA V2 or Invisible reCAPTCHA. 
	* @param secretKey Secret key (key given for communication between your site and Google) 
	* @param response reCAPTCHA response from client side. (g-recaptcha-response) 
	* @return true if validation successful, false otherwise. 
	*/ 
	public static boolean isCaptchaValid(String response) { 
	    try { 
	     String url = "https://www.google.com/recaptcha/api/siteverify?" 
	       + "secret=" + secretKey 
	       + "&response=" + response; 
	     InputStream res = new URL(url).openStream(); 
	     BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8"))); 

	     StringBuilder sb = new StringBuilder(); 
	     int cp; 
	     while ((cp = rd.read()) != -1) { 
	      sb.append((char) cp); 
	     } 
	     String jsonText = sb.toString(); 
	     res.close(); 

	     JSONObject json = new JSONObject(jsonText); 
	     return json.getBoolean("success"); 
	    } catch (Exception e) { 
	     return false; 
	    } 
	} 	
}
