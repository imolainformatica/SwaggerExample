package it.imolinfo.app;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

import java.io.IOException;

/**
 * https://stackoverflow.com/questions/24157817/jackson-databind-enum-case-insensitive
 * https://github.com/FasterXML/jackson-databind/issues/227
 * https://stackoverflow.com/questions/24208087/customize-jackson-unmarshalling-behavior
 * Created by morlins on 29/05/17.
 */
public class CustomBeanDeserializerModifierEnum extends BeanDeserializerModifier {
    @Override
    public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
                                                         final JavaType type,
                                                         BeanDescription beanDesc,
                                                         final JsonDeserializer<?> deserializer) {
        return new JsonDeserializer<Enum>() {
            @Override
            public Enum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase());
            }
        };
    }
}
