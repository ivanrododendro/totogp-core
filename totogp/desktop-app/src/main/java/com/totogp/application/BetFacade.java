package com.totogp.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.totogp.application.dto.CurrentBetTypeRP;
import com.totogp.application.dto.RiderRP;
import com.totogp.application.mapper.PodiumBetMapper;
import com.totogp.application.mapper.RiderMapper;
import com.totogp.business.BetBusiness;
import com.totogp.framework.exception.BusinessException;
import com.totogp.framework.persistence.DAO;
import com.totogp.model.Enrollment;
import com.totogp.model.PodiumBet;
import com.totogp.model.PoleBet;
import com.totogp.model.Race;
import com.totogp.model.Rider;
import com.totogp.model.WinnerBet;

@Path("/bet")
@Consumes("application/x-www-form-urlencoded")
@Produces({ "application/json" })
@Stateless
public class BetFacade extends RestFacade {

	@EJB
	private BetBusiness betBusiness;

	@Inject
	public DAO<Enrollment, Integer> enrrollmentDAO;

	@Inject
	private DAO<Rider, Integer> riderDao;

	@GET
	@Path("/current/type/enrollment/{id}")
	public Response getCurrentBetType(@PathParam("id") int id) {
		Enrollment enrollment = new Enrollment();
		enrollment.setId(id);

		return Response.ok(new CurrentBetTypeRP(betBusiness.getCurrentBetType(enrollment)))
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("/placePodiumBet")
	public void placePodiumBet(final Enrollment enrollment, final Race race, final Rider firstRider,
			final Rider secondRider, final Rider thirdRider) throws BusinessException {
		betBusiness.placePodiumBet(enrollment, race, firstRider, secondRider, thirdRider);
	}

	@POST
	@Path("/placePoleBet")
	public void placePoleBet(final Enrollment enrollment, final Race race, final Rider poleman)
			throws BusinessException {
		betBusiness.placePoleBet(enrollment, race, poleman);
	}

	@POST
	@Path("/placeWinnerBet")
	public void placeWinnerBet(@FormParam("enrollmnetId") int enrollmnetId, @FormParam("driverNumber") int driverNumber)
			throws BusinessException {
		Rider rider = riderDao.getSingleResult(Rider.GET_BY_NUMBER, driverNumber);
		Enrollment enrollment = enrrollmentDAO.find(enrollmnetId);

		betBusiness.placeWinnerBet(enrollment, rider);
	}

	@GET
	@Path("/winnerBet/{enrollmentId}")
	public Response getWinnerBet(@PathParam("enrollmentId") int enrollmentId) {
		WinnerBet currentBet = (WinnerBet) betBusiness.getCurrentBet(enrollmentId);

		if (currentBet == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response
				.ok(new RiderRP(currentBet.getWinner().getId(),
						currentBet.getWinner().getFirstname() + ' ' + currentBet.getWinner().getLastname(),
						currentBet.getWinner().getNickname(), currentBet.getWinner().getTwitterUser(),
						currentBet.getWinner().getTwitterHashtags(), null, currentBet.getWinner().getPictureUrl(),
						currentBet.getWinner().getCountry().getName()))
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/poleBet/{enrollmentId}")
	public Response getPoleBet(@PathParam("enrollmentId") int enrollmentId) {
		PoleBet currentBet = (PoleBet) betBusiness.getCurrentBet(enrollmentId);

		return Response.ok(RiderMapper.INSTANCE.riderToDTO(currentBet.getPoleman()))
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/podiumBet/{enrollmentId}")
	public Response getPodiumBet(@PathParam("enrollmentId") int enrollmentId) {
		PodiumBet currentBet = (PodiumBet) betBusiness.getCurrentBet(enrollmentId);

		return Response.ok(PodiumBetMapper.INSTANCE.betToDTO(currentBet)).header("Access-Control-Allow-Origin", "*")
				.build();
	}
}
