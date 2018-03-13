package com.totogp.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.totogp.dao.ContestDAO;
import com.totogp.framework.exception.BusinessException;
import com.totogp.framework.exception.TechnicalException;
import com.totogp.framework.persistence.DAO;
import com.totogp.model.Bet;
import com.totogp.model.Enrollment;
import com.totogp.model.PodiumBet;
import com.totogp.model.PoleBet;
import com.totogp.model.Race;
import com.totogp.model.Result;
import com.totogp.model.ResultType;
import com.totogp.model.Rider;
import com.totogp.model.WinnerBet;
import com.totogp.model.WinnerBlindBet;

@Stateless
public class BetBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(BetBusiness.class);

	@Inject
	private DAO<Bet, Long> betDao;

	@Inject
	private DAO<Result, Long> resultDao;

	@Inject
	private ContestDAO contestDAO;

	@Inject
	private DAO<Enrollment, Integer> enrrollmentDAO;

	// @Inject
	// private DAO<Statistic, Long> statisticDao;

	public void computeBetPoints() {
		final List<Bet> uncomputedBets = betDao.runQuery(Bet.GET_UNCOMPUTED, null);

		for (final Bet bet : uncomputedBets) {
			if (bet instanceof PoleBet) {
				computePoints((PoleBet) bet);
			} else if (bet instanceof WinnerBet) {
				computePoints((WinnerBet) bet);
			} else if (bet instanceof PodiumBet) {
				computePoints((PodiumBet) bet);
			} else if (bet instanceof WinnerBlindBet) {
				computPoints((WinnerBlindBet) bet);
			}
		}

	}

	private void computePoints(final PodiumBet bet) {
		int points = 0;

		// final List<Statistic> statistics =
		// statisticDao.runQuery(Statistic.GET_BY_ENROLLMENT, bet.getEnrollment());
		//
		// final Statistic newStat = new Statistic();

		final Result first = resultDao.getSingleResult(Result.GET_BY_RACE_TYPE, bet.getRace(), ResultType.FIRST);
		final Result second = resultDao.getSingleResult(Result.GET_BY_RACE_TYPE, bet.getRace(), ResultType.SECOND);
		final Result third = resultDao.getSingleResult(Result.GET_BY_RACE_TYPE, bet.getRace(), ResultType.THIRD);

		if (bet.getFirst().equals(first.getRider())) {
			points += 100;
			bet.setIsFirstWinning(Boolean.TRUE);
		} else if (bet.getFirst().equals(second.getRider())) {
			points += 66;
			bet.setIsFirstWinning(Boolean.FALSE);
		} else if (bet.getFirst().equals(third.getRider())) {
			points += 33;
			bet.setIsFirstWinning(Boolean.FALSE);
		}

		if (bet.getSecond().equals(first.getRider())) {
			points += 66;
			bet.setIsSecondWinning(Boolean.FALSE);
		} else if (bet.getSecond().equals(second.getRider())) {
			points += 100;
			bet.setIsSecondWinning(Boolean.TRUE);
		} else if (bet.getSecond().equals(third.getRider())) {
			points += 66;
			bet.setIsSecondWinning(Boolean.FALSE);
		}

		if (bet.getThird().equals(first.getRider())) {
			points += 33;
			bet.setIsThirdWinning(Boolean.FALSE);
		} else if (bet.getThird().equals(second.getRider())) {
			points += 66;
			bet.setIsThirdWinning(Boolean.FALSE);
		} else if (bet.getThird().equals(third.getRider())) {
			points += 100;
			bet.setIsThirdWinning(Boolean.TRUE);
		}

		bet.setPoints((float) points);
		bet.getEnrollment().setPoints(bet.getEnrollment().getPoints() + points);

		// final float winnerRate = 0;
		// final float poleRate =0;
		// final float firstRate =0;
		// final float secondRate =0;
		// final float thirdRate =0;
		//
		// final int winnerSuccesCount = 0;
		// final int poleSuccesCount = 0;
		// final int firstSuccesCount = 0;
		// final int secondSuccesCount = 0;
		// final int thirdSuccesCount = 0;
		//
		//
		// for (final Statistic statistic : statistics) {
		// if(statistic.get)
		// }
		//
		// newStat.setAfterRace(bet.getRace());
		// newStat.setEnrollment(bet.getEnrollment());
		// newStat.set
	}

	private void computePoints(final PoleBet bet) {
		int points = 0;

		final Result pole = resultDao.getSingleResult(Result.GET_BY_RACE_TYPE, bet.getRace(), ResultType.POLE);

		if (bet.getPoleman().equals(pole.getRider())) {
			bet.setIsWinning(Boolean.TRUE);
			points = 100;
		} else {
			bet.setIsWinning(Boolean.FALSE);
		}

		bet.getEnrollment().setPoints(bet.getEnrollment().getPoints() + points);

	}

	private void computePoints(final WinnerBet bet) {
		int points = 0;

		final Result pole = resultDao.getSingleResult(Result.GET_BY_RACE_TYPE, bet.getRace(), ResultType.FIRST);

		if (bet.getWinner().equals(pole.getRider())) {
			bet.setIsWinning(Boolean.TRUE);
			points = 200;
		} else {
			bet.setIsWinning(Boolean.FALSE);
		}

		bet.getEnrollment().setPoints(bet.getEnrollment().getPoints() + points);
	}

	private void computPoints(final WinnerBlindBet bet) {
		throw new TechnicalException("WinnerBlindBet not managaed yet!");
	}

	public String getCurrentBetType(Enrollment enrollment) {
		enrollment = enrrollmentDAO.find(enrollment.getId());

		return enrollment.getContest().getCurrentBetType();
	}

	public void placePodiumBet(final Enrollment enrollment, final Race race, final Rider firstRider,
			final Rider secondRider, final Rider thirdRider) throws BusinessException {

		// if (!userCanBet(enrollment, Bet.PODIUM_BET))
		// throw new BusinessException("user.alreadybet");

		final PodiumBet bet = new PodiumBet();

		bet.setBetDate(new Date());
		bet.setEnrollment(enrollment);
		bet.setIsWinning(false);
		bet.setFirst(firstRider);
		bet.setSecond(secondRider);
		bet.setThird(thirdRider);
		bet.setRace(race);
		bet.setType(Bet.PODIUM_BET);

		betDao.persist(bet);
	}

	public void placePoleBet(final Enrollment enrollment, final Race race, final Rider poleman)
			throws BusinessException {

		// if (!userCanBet(enrollment, Bet.POLE_BET))
		// throw new BusinessException("user.alreadybet");

		final PoleBet bet = new PoleBet();

		bet.setBetDate(new Date());
		bet.setEnrollment(enrollment);
		bet.setIsWinning(false);
		bet.setPoleman(poleman);
		bet.setRace(race);
		bet.setType(Bet.POLE_BET);

		betDao.persist(bet);
	}

	public void placeWinnerBet(final Enrollment enrollment, final Rider winnerRider) throws BusinessException {

		// if (!userCanBet(enrollment, Bet.WINNER_BET))
		// throw new BusinessException("user.alreadybet");

		final WinnerBet bet = new WinnerBet();

		bet.setBetDate(new Date());
		bet.setEnrollment(enrollment);
		bet.setIsWinning(false);
		bet.setWinner(winnerRider);
		bet.setRace(enrollment.getContest().getCurrentRace());
		bet.setType(Bet.WINNER_BET);

		betDao.persist(bet);
	}

	// public void placeWinnerBlindBet(final Enrollment enrollment, final Race race,
	// final Rider winnerBlindRider)
	// throws BusinessException {
	//
	// // if (!userCanBet(enrollment, Bet.WINNER_BET))
	// // throw new BusinessException("user.alreadybet");
	//
	// final WinnerBet bet = new WinnerBet();
	//
	// bet.setBetDate(new Date());
	// bet.setEnrollment(enrollment);
	// bet.setIsWinning(false);
	// bet.setWinner(winnerBlindRider);
	// bet.setRace(race);
	// bet.setType(Bet.WINNER_BLIND_BET);
	//
	// betDao.persist(bet);
	// }

	public Boolean userCanBet(Enrollment enrollment) {
		enrollment = enrrollmentDAO.find(enrollment.getId());

		LOGGER.info("open : " + enrollment.getContest().getOpen());

		return enrollment.getContest().getOpen();
	}

	public Bet getCurrentBet(int enrollmentId) {
		Enrollment enrollment = enrrollmentDAO.find(enrollmentId);

		if (enrollment == null)
			return null;

		return betDao.getSingleResult(Bet.GET_BY_ENRID_TYPE, enrollmentId, enrollment.getContest().getCurrentBetType());
	}
}
