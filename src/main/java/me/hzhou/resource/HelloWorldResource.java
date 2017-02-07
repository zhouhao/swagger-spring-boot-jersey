package me.hzhou.resource;

import io.swagger.annotations.ApiParam;
import me.hzhou.model.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hzhou on 2/7/17.
 * Email: i@hzhou.me
 */

@Component

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    @GET
    @Path("v1/hello/{name}")
    public Response getHelloVersionInUrl(@ApiParam @PathParam("name") String name) {
        LOGGER.info("getHelloVersionInUrl() v1");
        return this.getHello(name, "Version 1 - passed in URL");
    }

    @GET
    @Path("hello/{name}")
    @Consumes("application/vnd.asimio-v1+json")
    @Produces("application/vnd.asimio-v1+json")
    public Response getHelloVersionInAcceptHeader(@PathParam("name") String name) {
        LOGGER.info("getHelloVersionInAcceptHeader() v1");
        return this.getHello(name, "Version 1 - passed in Accept Header");
    }

    private Response getHello(String name, String partialMsg) {
        if ("404".equals(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Hello result = new Hello();
        result.setMsg(String.format("Hello %s. %s", name, partialMsg));
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
