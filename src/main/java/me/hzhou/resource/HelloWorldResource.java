package me.hzhou.resource;

import io.swagger.annotations.*;
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

@Api(value = "Hello resource", produces = "application/json")
@SwaggerDefinition(securityDefinition =
@SecurityDefinition(apiKeyAuthDefintions = {
        @ApiKeyAuthDefinition(key = "basic", name = "Authorization", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER)
}))
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    @GET
    @Path("hello/{name}")
    @ApiOperation(
            value = "Get a hello resource."
            , response = Hello.class
            //,responseContainer = "List" // if returned is a list of objects
            //,authorizations = @Authorization(value = "basic")
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hello resource found"),
            @ApiResponse(code = 404, message = "Given admin user not found")
    })
    public Response getHelloVersionInUrl(@ApiParam @PathParam("name") String name) {
        if ("404".equals(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Hello result = new Hello();
        result.setMsg(String.format("Hello %s. %s", name, "welcome to swagger"));
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
