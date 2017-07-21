package it.imolinfo.app;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
