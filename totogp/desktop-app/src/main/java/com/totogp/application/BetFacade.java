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
import com.totogp.business.BetBusiness;
import com.totogp.framework.exception.BusinessException;
import com.totogp.framework.persistence.DAO;
import com.totogp.model.Enrollment;
import com.totogp.model.Race;
import com.totogp.model.Rider;

@Path("/bet")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Stateless
public class BetFacade extends RestFacade {

	@EJB
	private BetBusiness betBusiness;

	@Inject
	private DAO<Enrollment, Integer> enrrollmentDAO;

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
	@Consumes("application/x-www-form-urlencoded")
	@Path("/placeWinnerBet")
	public void placeWinnerBet(@FormParam("enrollmnetId") int enrollmnetId, @FormParam("driverNumber") int driverNumber)
			throws BusinessException {
		Rider rider = riderDao.getSingleResult(Rider.GET_BY_NUMBER, driverNumber);
		Enrollment enrollment = enrrollmentDAO.find(enrollmnetId);

		betBusiness.placeWinnerBet(enrollment, rider);
	}
}
