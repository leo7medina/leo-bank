package ec.com.leo.bank.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.TimeZone;

public final class MapperBuilder {

    private static final MapperBuilder INSTANCE = new MapperBuilder();

    /**
     * Constructor.
     */
    private MapperBuilder() {
    }

    /**
     * Get instance.
     *
     * @return MapperBuilder
     */
    public static MapperBuilder getInstance() {
        return INSTANCE;
    }

    /**
     * Construye un ObjectMapper de Jackson especificando si quiere que se excluya
     * los atributos nulables de un objeto en la serializacion a JSON.
     *
     * @return ObjectMapper
     */
    public ObjectMapper buildMapper() {
        ObjectMapper mapper = new ObjectMapper();
        buildMapper(mapper, Boolean.TRUE);
        return mapper;
    }

    /**
     * Construye un ObjectMapper de Jackson especificando si quiere que se excluya.
     * los atributos nulables de un objeto en la serializacion a JSON.
     *
     * @param excludeNullableAttributes atributos a excluir
     * @return ObjectMapper
     */
    public ObjectMapper buildMapper(boolean excludeNullableAttributes) {
        ObjectMapper mapper = new ObjectMapper();
        buildMapper(mapper, excludeNullableAttributes);

        return mapper;
    }

    /**
     * Construye un ObjectMapper de Jackson especificando si quiere que se excluya.
     * los atributos nulables de un objeto en la serializacion a JSON.
     *
     * @param mapper objeto mapper
     * @param excludeNullableAttributes atributos a excluir
     */
    private static void buildMapper(ObjectMapper mapper, boolean excludeNullableAttributes) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, Boolean.TRUE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
        mapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, Boolean.FALSE);
        mapper.setTimeZone(TimeZone.getTimeZone("GMT-5"));

        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        if (excludeNullableAttributes) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }
}
