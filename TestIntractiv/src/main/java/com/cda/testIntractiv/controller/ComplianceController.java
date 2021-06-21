package com.cda.testIntractiv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cda.testIntractiv.model.Compliance;
import com.cda.testIntractiv.model.Password;

@RestController
public class ComplianceController {

	@GetMapping("/api/compliance/password/{password}")
	public Compliance verifyCompliance(@PathVariable String password) {
		Password pwd = new Password();
		Compliance compliance = pwd.testCompliance(password);

		return compliance;

	}

}
