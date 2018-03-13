package com.totogp.application.dto;

public class LoginMessageRP {
	private Long contestId;

	public Long getContestId() {
		return contestId;
	}

	public void setContestId(Long contestId) {
		this.contestId = contestId;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getContestLabel() {
		return contestLabel;
	}

	public void setContestLabel(String contestLabel) {
		this.contestLabel = contestLabel;
	}

	private String email;
	private Integer enrollmentId;
	private String firstname;
	private String lastname;
	private Integer points;
	private Long userId;
	private String contestLabel;
	private Integer ranking;
	private String raceLabel;

	public String getRaceLabel() {
		return raceLabel;
	}

	public void setRaceLabel(String raceLabel) {
		this.raceLabel = raceLabel;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public LoginMessageRP() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public Integer getEnrollmentId() {
		return enrollmentId;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Long getUserId() {
		return userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnrollmentId(Integer enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
