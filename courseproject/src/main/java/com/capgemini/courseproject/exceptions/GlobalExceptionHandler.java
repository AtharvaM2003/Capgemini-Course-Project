package com.capgemini.courseproject.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String TIMESTAMP = "timestamp";
	private static final String MESSAGE = "message";
	private static final String STATUS = "status";

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserExists(UserAlreadyExistsException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(SubmissionNotFoundException.class)
	public ResponseEntity<Object> handleSubmissionNotFound(SubmissionNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InstructorNotFoundException.class)
	public ResponseEntity<Object> handleInstructorNotFound(InstructorNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EnrollmentNotFoundException.class)
	public ResponseEntity<Object> handleEnrollmentNotFound(EnrollmentNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<Object> handleCourseNotFound(CourseNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AssignmentNotFoundException.class)
	public ResponseEntity<Object> handleAssignmentNotFound(AssignmentNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException i) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, i.getMessage());
		errorDetails.put(STATUS, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(STATUS, status.value());

		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

		errorDetails.put("errors", fieldErrors);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(MESSAGE, "Unexpected error occurred");
		errorDetails.put("details", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
