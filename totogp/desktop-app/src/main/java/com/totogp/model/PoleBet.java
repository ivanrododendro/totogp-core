package com.totogp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = Bet.POLE_BET)
public class PoleBet extends Bet {
  @OneToOne(fetch = FetchType.LAZY)
  private Rider poleman;

  public Rider getPoleman() {
    return poleman;
  }

  public void setPoleman(final Rider poleman) {
    this.poleman = poleman;
  }
}
