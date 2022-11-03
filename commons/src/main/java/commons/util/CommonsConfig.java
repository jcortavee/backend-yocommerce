package commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.exceptions.models.ErrorMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class CommonsConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
