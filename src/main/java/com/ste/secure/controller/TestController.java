package com.ste.secure.controller;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableWebSecurity
@EnableMethodSecurity
public class TestController {

	@GetMapping("/test")
	public String getMethodName(Authentication authentication) {
		
		return "Test ok || "+authentication.getName()+" || detail : "+authentication.getDetails();
	}
	
	@GetMapping("/login/{pass}/{login}")
	public String getLogin(@PathVariable("pass") String pass,@PathVariable("login") String login ) {
		return "login okee== "+login+" || "+pass;
	}
	
	@GetMapping("/open/{data}")
	public String getOpenEndPoint(@PathVariable("data") String data) {
		return data;
	}
	
	
	
	@GetMapping("/admin/{data}")
	public String getAdmin(@PathVariable("data") String data) {
		return data;
	}
	
	
	 @GetMapping("/public/page")
	 public ResponseEntity<String> getBonjourPage() {
        try {
            Resource resource = new ClassPathResource("static/index.html");
            Path path = resource.getFile().toPath();
            String content = Files.readString(path, StandardCharsets.UTF_8);
            return ResponseEntity.ok().body(content);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors du chargement de la page");
        }
	  }
	
}
