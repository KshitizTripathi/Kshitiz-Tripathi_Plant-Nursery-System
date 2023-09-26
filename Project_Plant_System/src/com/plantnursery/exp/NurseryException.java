package com.plantnursery.exp;

public class NurseryException extends Exception {
	String msg;
	public NurseryException(String str) {
		msg=str;
	}
	public String toString() {
		return msg;
	}
}
