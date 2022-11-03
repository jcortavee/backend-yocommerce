package commons.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
    private HttpStatus status;
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

}
