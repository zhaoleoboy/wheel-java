package com.leo.wheel.entity.common;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 相应ID
	private String code = "99999";// 状态码
	private String message = "";// 状态码描述
	private T result = null;// 结果集

	public RestResponse() {

	}

	public RestResponse(T result) {
		this.result = result;
	}

	public RestResponse(String code, T result) {
		this.code = code;
		this.result = result;
		this.message = ResponseMessage.getInstance().getValue(code);
	}

	public RestResponse(String code, String message, T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public static <T> RestResponse<T> result(T result) {
		return new RestResponse<T>(result);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}

}
