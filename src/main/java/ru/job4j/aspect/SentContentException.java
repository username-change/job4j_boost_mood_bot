package ru.job4j.aspect;

public class SentContentException extends RuntimeException {
	public SentContentException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
