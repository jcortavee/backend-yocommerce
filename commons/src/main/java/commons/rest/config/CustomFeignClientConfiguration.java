package commons.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.exceptions.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CustomFeignClientConfiguration {

    private final ObjectMapper objectMapper;

    public CustomFeignClientConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder(objectMapper);
    }

}
