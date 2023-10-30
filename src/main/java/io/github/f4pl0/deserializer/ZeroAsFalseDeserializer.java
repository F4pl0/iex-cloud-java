package io.github.f4pl0.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Jackson deserializer for boolean values that are represented as 0 or 1.
 * @hidden This class is not part of the public API. <b>DO NOT USE</b>.
 */
public class ZeroAsFalseDeserializer extends JsonDeserializer<Boolean> {

    /**
     * Deserializes a boolean value that is represented as 0 or 1.
     * @param jsonParser Parser used for reading JSON content
     * @param deserializationContext Context that can be used to access information about this deserialization activity.
     *
     * @return A boolean value.
     * @throws IOException If the request fails.
     */
    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int value = jsonParser.getIntValue();
        return value != 0;
    }
}