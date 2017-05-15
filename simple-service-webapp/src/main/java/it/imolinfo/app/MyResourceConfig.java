package it.imolinfo.app;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by morlins on 12/05/17.
 */

public class MyResourceConfig extends ResourceConfig {
    public MyResourceConfig() {
        super(

                MyObjectMapperProvider.class,
                //      ExceptionMappingTestResource.class,
                JacksonFeature.class
        );
    }
}
