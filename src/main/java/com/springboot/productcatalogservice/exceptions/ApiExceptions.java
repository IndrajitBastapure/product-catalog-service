package com.springboot.productcatalogservice.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.springboot.productcatalogservice.LowerCaseClassNameResolver;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiExceptions {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private List<ApiSubExceptions> subExceptions;
	
	public ApiExceptions() {
		timestamp = LocalDateTime.now();
	}
	
	public ApiExceptions(HttpStatus status) {
		this();
        this.status = status;
	}
	
	public ApiExceptions(HttpStatus status, Throwable ex) {
		this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
	}
	
	public ApiExceptions(HttpStatus status, String message, Throwable ex) {
		this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
	}
	
	
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public List<ApiSubExceptions> getSubExceptions() {
		return subExceptions;
	}

	public void setSubExceptions(List<ApiSubExceptions> subExceptions) {
		this.subExceptions = subExceptions;
	}

	private void addSubExceptions(ApiSubExceptions subException) {
        if (subExceptions == null) {
        	subExceptions = new ArrayList<>();
        }
        subExceptions.add(subException);
    }

    private void addValidationException(String object, String field, Object rejectedValue, String message) {
    	addSubExceptions(new ApiValidationException(object, field, rejectedValue, message));
    }

    private void addValidationException(String object, String message) {
    	addSubExceptions(new ApiValidationException(object, message));
    }

    private void addValidationException(FieldError fieldError) {
        this.addValidationException(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationExceptions(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationException);
    }

    private void addValidationException(ObjectError objectError) {
        this.addValidationException(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationException);
    }

    
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationException(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

}
