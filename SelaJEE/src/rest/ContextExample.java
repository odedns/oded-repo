/**
 * 
 */
package rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
 
@Path("/users")
public class ContextExample {
 
	@GET
	@Path("/get")
	public Response addUser(@Context HttpHeaders headers) {
 
		String userAgent = headers.getRequestHeader("user-agent").get(0);
 
		return Response.status(200)
			.entity("addUser is called, userAgent : " + userAgent)
			.build();
 
	}
 
}