package com.totogp.application;

import javax.ws.rs.Path;

import com.totogp.business.BetBusiness;
import com.totogp.framework.exception.BusinessException;
import com.totogp.model.Championship;
import com.totogp.model.Enrollment;
import com.totogp.model.Race;
import com.totogp.model.Regulation;
import com.totogp.model.Rider;

@Path("/bet/business")
public class BetFacade {

  private BetBusiness betBusiness;

  public String getCurrentBetType(
      final Regulation regulation,
      final Integer year,
      final Race currentRace,
      final Championship championship) {
    return betBusiness.getCurrentBetType(regulation, year, currentRace, championship);
  }

  public void placePodiumBet(
      final Enrollment enrollment,
      final Race race,
      final Rider firstRider,
      final Rider secondRider,
      final Rider thirdRider) throws BusinessException {
    betBusiness.placePodiumBet(enrollment, race, firstRider, secondRider, thirdRider);
  }

  public void placePoleBet(final Enrollment enrollment, final Race race, final Rider poleman) throws BusinessException {
    betBusiness.placePoleBet(enrollment, race, poleman);
  }

  public void placeWinnerBet(final Enrollment enrollment, final Race race, final Rider winnerRider)
      throws BusinessException {
    betBusiness.placeWinnerBet(enrollment, race, winnerRider);
  }

  public void placeWinnerBlindBet(final Enrollment enrollment, final Race race, final Rider winnerBlindRider)
      throws BusinessException {
    betBusiness.placeWinnerBlindBet(enrollment, race, winnerBlindRider);
  }

  public Boolean userHasToBet(final Enrollment enrollment, final String betType) {
    return betBusiness.userCanBet(enrollment, betType);
  }

}
