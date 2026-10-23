package com.internship.internship_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InternshipTrackerApplication {

	@GetMapping("/")
	public String demo() {
		return "Internship Tracker API is running!";
	}

	public static void main(String[] args) {
		SpringApplication.run(InternshipTrackerApplication.class, args);
	}
}
