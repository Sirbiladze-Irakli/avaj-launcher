package ru.jormond.avaj_launcher.exceptions;

public class ValidationException extends Exception {

	public ValidationException() {
		super();
	}

	public ValidationException(String exception) {
		super(exception);
	}

	public ValidationException(Throwable exception) {
		super(exception);
	}
}
