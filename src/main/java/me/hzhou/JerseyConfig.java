package me.hzhou;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import me.hzhou.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by hzhou on 2/7/17.
 * Email: i@hzhou.me
 */

@Component
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    public JerseyConfig() {
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        // Register components where DI is needed
        this.configureSwagger();
    }

    private void registerEndpoints() {
        this.register(HelloWorldResource.class);
        // Available at /<Jersey's servlet path>/application.wadl
        this.register(WadlResource.class);
    }

    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-docker-example");
        config.setTitle("Spring Boot + Jersey + Swagger + Docker Example");
        config.setVersion("v1");
        config.setContact("Hao Zhou");
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath(this.apiPath);
        config.setResourcePackage("me.hzhou.resource");
        config.setPrettyPrint(true);
        config.setScan(true);

        // it does not work as below
        //config.getSwagger().setSecurityDefinitions(XX);

        // if you want to have you pojo parse as SNAKE_CASE, please add following line,
        // also in this case, you should use swagger.yaml, rather than swagger.json for output, as
        // some swagger keywords, such as operationId, will be parsed as operation_id. - syntax error
        //io.swagger.util.Json.mapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

}
