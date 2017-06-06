package it.imolinfo.util;

/**
 * Created by morlins on 12/05/17.
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.swagger.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;

/**
 * TODO javadoc.
 * https://github.com/jersey/jersey/tree/2.25.1/examples/json-jackson
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
@Provider

public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {
    static final Logger LOG = LoggerFactory.getLogger(MyObjectMapperProvider.class);
    final ObjectMapper defaultObjectMapper;
    final ObjectMapper combinedObjectMapper;

    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
        combinedObjectMapper = createCombinedObjectMapper();
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        LOG.debug("-------------------");
    /*    if (type == CombinedAnnotationBean.class) {
            return combinedObjectMapper;
        } else {*/
            return combinedObjectMapper;
     //   }
    }

    private static ObjectMapper createCombinedObjectMapper() {
        ObjectMapper om= Json.mapper();
        om.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//                .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING,true)
//                .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true)
//        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS,true);
        om.configure(SerializationFeature.INDENT_OUTPUT,true);
             //   .setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
        LOG.debug("oooooooooooooooooooooooo");
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new CustomBeanDeserializerModifierEnum());
        module.addSerializer(Enum.class, new StdSerializer<Enum>(Enum.class) {
            @Override
            public void serialize(Enum value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeString(value.name().toLowerCase());
            }
        });
        om.registerModule(module);
        return om;
    }

    private static ObjectMapper createDefaultMapper() {
        LOG.debug("ooooooooooddddddddddoooooooooooooo");
        final ObjectMapper result = new ObjectMapper();
        result.enable(SerializationFeature.INDENT_OUTPUT);

        return result;
    }
/*
    private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {

        final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

        return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
    }
    */
}
