package com.toy1.web.service;

public interface EmailSenderService {

	//이메일 발송
	String templateSendEmail(String to, String name) throws Exception;
	
}//interface ends
