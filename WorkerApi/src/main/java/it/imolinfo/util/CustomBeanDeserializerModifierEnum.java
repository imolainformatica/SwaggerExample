package it.imolinfo.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * https://stackoverflow.com/questions/24157817/jackson-databind-enum-case-insensitive
 * https://github.com/FasterXML/jackson-databind/issues/227
 * https://stackoverflow.com/questions/24208087/customize-jackson-unmarshalling-behavior
 * Created by morlins on 29/05/17.
 */
public class CustomBeanDeserializerModifierEnum extends BeanDeserializerModifier {
    static final Logger LOG = LoggerFactory.getLogger(CustomBeanDeserializerModifierEnum.class);
    @Override
    public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
                                                         final JavaType type,
                                                         BeanDescription beanDesc,
                                                         final JsonDeserializer<?> deserializer) {
        return new JsonDeserializer<Enum>() {
            @Override
            public Enum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                LOG.debug("here!!!!!!!!!!!!1");
                Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase());
            }
        };
    }
}
