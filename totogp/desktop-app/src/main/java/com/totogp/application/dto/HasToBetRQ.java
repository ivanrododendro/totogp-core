package com.totogp.application.dto;

public class HasToBetRQ {
	@Override
	public String toString() {
		return "HasToBetRQ [enrollmentId=" + enrollmentId + "]";
	}

	public HasToBetRQ() {
		super();
	}

	public Integer getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Integer enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	private Integer enrollmentId;
}
