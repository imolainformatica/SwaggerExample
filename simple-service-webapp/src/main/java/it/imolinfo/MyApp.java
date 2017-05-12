package it.imolinfo;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by morlins on 12/05/17.
 */

public class MyApp extends ResourceConfig {
    public MyApp() {
        super(

                MyObjectMapperProvider.class,
          //      ExceptionMappingTestResource.class,
                JacksonFeature.class
        );
    }
}
