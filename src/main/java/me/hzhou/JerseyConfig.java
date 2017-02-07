package me.hzhou;

import me.hzhou.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

/**
 * Created by hzhou on 2/7/17.
 * Email: i@hzhou.me
 */

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.registerEndpoints();
    }

    private void registerEndpoints() {
        this.register(HelloWorldResource.class);
        // Available at /<Jersey's servlet path>/application.wadl
        this.register(WadlResource.class);
    }
}
