package com.cda.testIntractiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compliance {

	private boolean isValid;
	private String reason;

	

}
