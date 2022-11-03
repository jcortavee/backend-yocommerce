package commons.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.exceptions.models.ErrorMessageResponse;
import commons.util.CommonsConfig;
import commons.util.enums.Charsets;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public CustomErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public Exception decode(String s, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        if (status == HttpStatus.NOT_FOUND) {
            try {
                var message = decodeErrorMessageResponse(response.body().asInputStream());
                var responseMessage = message.isPresent() ? message.get().getMessage() : "Record not found";
                return new RecordNotFoundException(responseMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new Exception("Generic exception");
        }
    }

    private Optional<ErrorMessageResponse> decodeErrorMessageResponse(InputStream inputStream) {
        ErrorMessageResponse message = null;
        try {
            var json = IOUtils.toString(inputStream, Charsets.UTF_8);
            message = objectMapper.readValue(json, ErrorMessageResponse.class);

        } catch (IOException e) {
            log.error("Error in deserialization the object");
        }

        return Optional.ofNullable(message);
    }
}
