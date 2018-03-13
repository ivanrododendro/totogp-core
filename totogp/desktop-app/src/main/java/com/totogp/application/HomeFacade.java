package com.totogp.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.totogp.application.dto.RankingRP;
import com.totogp.application.dto.RiderRP;
import com.totogp.application.mapper.RankingMapper;
import com.totogp.framework.persistence.DAO;
import com.totogp.model.Enrollment;
import com.totogp.model.Rider;
import com.totogp.model.Statistic;

@Stateless
@Path("/home")
@Produces({ "application/json" })
public class HomeFacade extends RestFacade {

	@Inject
	private DAO<Statistic, Long> statisticsDao;

	@Inject
	private DAO<Rider, Long> riderDao;

	@Inject
	private DAO<Enrollment, Long> enrollmentDao;

	public List<Statistic> getUserStatistics(final Enrollment enrollment) {
		return statisticsDao.runQuery(Statistic.GET_BY_ENROLLMENT, enrollment);
	}

	@GET
	@Path("ranking/{contestId}")
	public Response getRanking(@PathParam("contestId") long contestId) {
		List<RankingRP> result = new ArrayList<>();

		for (Enrollment enrollment : enrollmentDao.runQuery(Enrollment.GET_BY_CONTEST_ID, contestId)) {
			result.add(RankingMapper.INSTANCE.riderToDTO(enrollment));
		}

		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("riders")
	public Response getRiderList() {
		List<RiderRP> response = new ArrayList<>();
		List<Rider> riders = riderDao.findAll();

		for (Rider rider : riders) {
			RiderRP riderRP = new RiderRP();

			riderRP.setId(rider.getNumber());
			riderRP.setCountry(rider.getCountry().getName());
			riderRP.setPictureUrl(rider.getPictureUrl());
			riderRP.setName(rider.getFirstname() + ' ' + rider.getLastname());

			response.add(riderRP);
		}

		return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();
	}
}
