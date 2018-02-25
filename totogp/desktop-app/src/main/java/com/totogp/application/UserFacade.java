package com.totogp.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.totogp.application.dto.HasToBetRQ;
import com.totogp.application.dto.LoginMessageRP;
import com.totogp.application.dto.LoginMessageRQ;
import com.totogp.application.mapper.LoginMessageMapper;
import com.totogp.business.BetBusiness;
import com.totogp.business.UserBusiness;
import com.totogp.dao.UserDAO;
import com.totogp.framework.exception.BusinessException;
import com.totogp.model.Contest;
import com.totogp.model.Enrollment;
import com.totogp.model.User;

@Path("/user")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Stateless
public class UserFacade {

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

	@POST
	@Path("canBet")
	public Boolean userCanBet(HasToBetRQ request) {
		Enrollment enrollment = new Enrollment();
		enrollment.setId(request.getEnrollmentId());

		return betBusiness.userCanBet(enrollment);
	}

}
