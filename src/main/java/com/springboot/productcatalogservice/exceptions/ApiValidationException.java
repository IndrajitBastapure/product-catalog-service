package com.springboot.productcatalogservice.exceptions;

import java.util.Objects;

public class ApiValidationException extends ApiSubExceptions {

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	ApiValidationException(String object, String message) {
        this.object = object;
        this.message = message;
    }
	
	ApiValidationException(String object,String field,Object rejectedValue,String message) {
        this.object = object;
        this.message = message;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(field, message, object, rejectedValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiValidationException other = (ApiValidationException) obj;
		return Objects.equals(field, other.field) && Objects.equals(message, other.message)
				&& Objects.equals(object, other.object) && Objects.equals(rejectedValue, other.rejectedValue);
	}

}
