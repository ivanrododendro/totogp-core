package com.totogp.application.dto;

public class RankingRP {
	private String userLabel;
	private int points;

	public RankingRP() {
		super();
	}

	@Override
	public String toString() {
		return "RankingRP [userLabel=" + userLabel + ", points=" + points + "]";
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
