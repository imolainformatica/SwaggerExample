package it.imolinfo.app;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.swagger.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by morlins on 30/05/17.
 */
@Provider
public class CustomJsonProvider implements ContextResolver<ObjectMapper> {
    static final Logger LOG = LoggerFactory.getLogger(CustomJsonProvider.class);
    private static ObjectMapper mapper = CustomObjectMapper.get();
    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}
