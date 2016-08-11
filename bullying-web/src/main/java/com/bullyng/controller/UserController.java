package com.bullyng.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullying.security.UserSession;

/**
 * 
 * @author juan.castano
 *
 */
@Component
@Path("/private/userController")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserController {
	
	@Autowired
	private UserSession userSession;
	
	/**
	 * @author juan.castano
	 */
	@GET
    @Path("pruebaSeguridad")
    public void pruebaSeguridad() {
        System.out.println(userSession.getUserSession().getName());
    }
}
