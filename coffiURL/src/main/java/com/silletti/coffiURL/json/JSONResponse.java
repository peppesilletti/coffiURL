package com.silletti.coffiURL.json;

public class JSONResponse<T> {

	private String response;
	private Integer code;
	private T data;
	
	
	
	public JSONResponse(String response, Integer code, T data) {
		super();
		this.response = response;
		this.code = code;
		this.data = data;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
	
}
