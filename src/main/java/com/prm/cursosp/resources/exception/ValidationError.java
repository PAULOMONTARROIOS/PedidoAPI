package com.prm.cursosp.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> fieldMessage = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return fieldMessage;
	}

	public void addError(String fieldName, String message) {
		getErros().add(new FieldMessage(fieldName, message));
	}

}
