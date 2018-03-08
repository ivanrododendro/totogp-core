package com.totogp.application.dto;

public class CurrentBetTypeRP {
	private String currentBetType;

	public CurrentBetTypeRP() {
		super();
	}

	public CurrentBetTypeRP(String currentBetType) {
		super();
		this.currentBetType = currentBetType;
	}

	@Override
	public String toString() {
		return "CurrentBetTypeRP [currentBetType=" + currentBetType + "]";
	}

	public String getCurrentBetType() {
		return currentBetType;
	}

	public void setCurrentBetType(String currentBetType) {
		this.currentBetType = currentBetType;
	}
}
