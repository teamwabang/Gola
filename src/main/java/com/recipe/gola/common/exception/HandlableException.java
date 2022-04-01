package com.recipe.gola.common.exception;

import com.recipe.gola.common.code.ErrorCode;

@SuppressWarnings("serial")
public class HandlableException extends RuntimeException{
	
	public ErrorCode error;
	
	// log를 남기지 않고 사용자에게 예외를 전달하기 위한 생성자
	public HandlableException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]); //stack trace를 날린다.
	}
	
	// log를 남겨야 하는 경우 사용자에게 예외를 전달하기 위한 생성자
	public HandlableException(ErrorCode error, Exception e) {
		this.error = error;
		e.printStackTrace();
		this.setStackTrace(new StackTraceElement[0]); //stack trace를 날린다.
	}
	
	
	
	
	
	
	
	
}
