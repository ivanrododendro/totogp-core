package com.totogp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = Bet.WINNER_BET)
public class WinnerBet extends Bet {
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "winner_rider_id", foreignKey = @ForeignKey(name = "FK_BET_WINNER_RIDER") )
  private Rider winner;

  public Rider getWinner() {
    return winner;
  }

  public void setWinner(final Rider winner) {
    this.winner = winner;
  }
}
