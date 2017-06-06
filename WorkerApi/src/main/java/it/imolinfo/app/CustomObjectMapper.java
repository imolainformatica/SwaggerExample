package it.imolinfo.app;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.util.Json;

/**
 * Created by morlins on 01/06/17.
 */
public class CustomObjectMapper {

    private CustomObjectMapper (){}
    public static ObjectMapper get(){

        ObjectMapper mapper = Json.mapper();
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
