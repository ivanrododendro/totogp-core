package com.totogp.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.totogp.application.dto.LoginMessageRP;
import com.totogp.application.dto.LoginMessageRQ;
import com.totogp.application.dto.UserCanBetRP;
import com.totogp.application.mapper.LoginMessageMapper;
import com.totogp.business.BetBusiness;
import com.totogp.business.UserBusiness;
import com.totogp.dao.UserDAO;
import com.totogp.framework.exception.BusinessException;
import com.totogp.model.Contest;
import com.totogp.model.Enrollment;
import com.totogp.model.User;

@Path("/user")
@Produces({ "application/json" })
@Stateless
public class UserFacade extends RestFacade {

	@EJB
	private UserBusiness userBusiness;

	@EJB
	private BetBusiness betBusiness;

	@Inject
	private UserDAO userDAO;

	public void activate(final String email, final String token) throws BusinessException {
		userBusiness.activate(email, token);
	}

	public void delete(final User user) {
		userBusiness.delete(user);
	}

	public void enroll(final User user, final Contest contest) {
		userBusiness.enroll(user, contest);
	}

	@POST
	@Path("login")
	public LoginMessageRP login(LoginMessageRQ message) throws BusinessException {
		// User user = userBusiness.login(mailAdress, password);
		User user = userDAO.getSingleResult(User.GET_BY_EMAIL, message.getEmail());

		return LoginMessageMapper.INSTANCE.carToCarDto(user);
	}

	public User registerNew(final User newUser) {
		return userBusiness.registerNew(newUser);
	}

	@GET
	@javax.ws.rs.Path("{id}/canBet")
	public Response userCanBet(@javax.ws.rs.PathParam("id") String id) {
		Enrollment enrollment = new Enrollment();
		enrollment.setId(Integer.parseInt(id));

		return Response.ok(new UserCanBetRP(betBusiness.userCanBet(enrollment)))
				.header("Access-Control-Allow-Origin", "*").build();
	}
}
