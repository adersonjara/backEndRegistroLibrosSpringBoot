package com.app.registrolibros.web.app.payload;

public class ApiResponse<T> {
	
    private String statusCode;
    private T data;
    private String message;
    private String error;

    public ApiResponse(String statusCode, T data, String message, String error) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.error = error;
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
