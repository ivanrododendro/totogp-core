package com.totogp.application.dto;

public class UserCanBetRP {
	private Boolean userCanBet;

	public UserCanBetRP() {
		super();
	}

	public UserCanBetRP(Boolean userCanBet) {
		super();
		this.userCanBet = userCanBet;
	}

	@Override
	public String toString() {
		return "CanBetRP [userCanBet=" + userCanBet + "]";
	}

	public Boolean getUserCanBet() {
		return userCanBet;
	}

	public void setUserCanBet(Boolean userCanBet) {
		this.userCanBet = userCanBet;
	}
}
