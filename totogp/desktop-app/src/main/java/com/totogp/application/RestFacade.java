package com.totogp.application;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class RestFacade {

	@OPTIONS
	@Path("{path:.*}")
	public Response options() {
		Response r = Response.ok().header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET").build();
	
		return r;
	}

}
