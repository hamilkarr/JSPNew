package com.exception;

/** 
 * 소셜 로그인 전용 예외  
 *	+ 나중에 로거 추가 가능
 */
public class SocialLoginException extends Exception {
	public SocialLoginException(String message) {
		super(message);
	}
}
