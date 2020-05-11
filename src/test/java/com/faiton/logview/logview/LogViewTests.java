package com.faiton.logview.logview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faiton.logview.logview.controller.LogController;

@SpringBootTest
class LogViewTests {

	@Autowired
	private LogController logController;

	@Test
	void contextLoads() {
	}

	@Test
	public void listLogs() {
		logController.logs();
	}

}
