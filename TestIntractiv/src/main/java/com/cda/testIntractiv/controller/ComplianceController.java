package com.cda.testIntractiv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cda.testIntractiv.model.Compliance;
import com.cda.testIntractiv.model.Password;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(description = "tools to verify the compliance rules")
@RestController
public class ComplianceController {

	@GetMapping("/api/compliances/passwords/{password}")
	@Operation(summary = "Return true if password is compliant or false and reasons if not, Complexity = at least 8 characters at least one digit at least one special character (!,#,$,%,&,@) at least one uppercase letter")
	public Compliance verifyCompliance(@PathVariable String password) {
		Password pwd = new Password();
		Compliance compliance = pwd.testCompliance(password);

		return compliance;

	}

}
