package it.imolinfo.util;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by morlins on 12/05/17.
 */

public class MyResourceConfig extends ResourceConfig {
    static final Logger LOG = LoggerFactory.getLogger(MyResourceConfig.class);
    public MyResourceConfig() {
        super(
               //      ExceptionMappingTestResource.class,
                CustomMarshallingFeature.class,
//                MyObjectMapperProvider.class,
                JacksonFeature.class
        );
        // create custom ObjectMapper
        LOG.debug("iiiiiii");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
//        // create JsonProvider to provide custom ObjectMapper
//        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
//        provider.setMapper(mapper);
//        register(provider);
//        register(CustomMarshallingFeature.class);
    }
}
