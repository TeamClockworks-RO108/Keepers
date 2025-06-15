package ro.clockworks.keepers.mock;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.clockworks.keepers.mock.data.modify.ItemQuantityModifyBase;
import ro.clockworks.keepers.mock.data.modify.ItemQuantityModifyDeserializer;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapper() {
        return builder -> {
            builder.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            builder.featuresToEnable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
            builder.featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            builder.featuresToEnable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
            builder.featuresToEnable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ItemQuantityModifyBase.class, new ItemQuantityModifyDeserializer());
            builder.modules(module);
        };
    }

}
