package com.internship.internship_tracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InternshipTrackerApplicationTests {

	@Test
	void applicationClassExists() {
		// Verify the main application class exists and can be loaded
		assertTrue(InternshipTrackerApplication.class.isAnnotationPresent(
				org.springframework.boot.autoconfigure.SpringBootApplication.class));
	}
}
