package com.totogp.model;

//@Entity
//@DiscriminatorValue(value = Bet.WINNER_BLIND_BET)
public class WinnerBlindBet extends Bet {
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "winner_blind_rider_id", foreignKey = @ForeignKey(name =
	// "FK_BET_WINNER_RIDER"))
	private Rider winnerBlind;

	public Rider getWinnerBlind() {
		return winnerBlind;
	}

	public void setWinnerBlind(final Rider winnerBlind) {
		this.winnerBlind = winnerBlind;
	}
}
