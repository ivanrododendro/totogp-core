package com.totogp.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = Bet.PODIUM_BET)
public class PodiumBet extends Bet {

  @ManyToOne(fetch = FetchType.EAGER)
  private Rider first;

  @ManyToOne(fetch = FetchType.EAGER)
  private Rider second;

  @ManyToOne(fetch = FetchType.EAGER)
  private Rider third;

  @Column(name = "is_first_winning", nullable = true)
  private Boolean isFirstWinning;

  @Column(name = "is_second_winning", nullable = true)
  private Boolean isSecondWinning;

  @Column(name = "is_third_winning", nullable = true)
  private Boolean isThirdWinning;

  public Rider getFirst() {
    return first;
  }

  public Boolean getIsFirstWinning() {
    return isFirstWinning;
  }

  public Boolean getIsSecondWinning() {
    return isSecondWinning;
  }

  public Boolean getIsThirdWinning() {
    return isThirdWinning;
  }

  public Rider getSecond() {
    return second;
  }

  public Rider getThird() {
    return third;
  }

  public void setFirst(final Rider first) {
    this.first = first;
  }

  public void setIsFirstWinning(final Boolean isFirstWinning) {
    this.isFirstWinning = isFirstWinning;
  }

  public void setIsSecondWinning(final Boolean isSecondWinning) {
    this.isSecondWinning = isSecondWinning;
  }

  public void setIsThirdWinning(final Boolean isThirdWinning) {
    this.isThirdWinning = isThirdWinning;
  }

  public void setSecond(final Rider second) {
    this.second = second;
  }

  public void setThird(final Rider third) {
    this.third = third;
  }

}
