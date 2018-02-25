package com.totogp.application.dto;

public class LoginMessageRP {
	private String firstname;

	@Override
	public String toString() {
		return "LoginMessageRP [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", enrollmentId=" + enrollmentId + ", userId=" + userId + "]";
	}

	public LoginMessageRP() {
		super();
	}

	private String lastname;
	private String email;
	private Long enrollmentId;
	private Long userId;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
