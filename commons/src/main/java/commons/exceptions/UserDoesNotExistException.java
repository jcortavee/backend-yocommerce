package commons.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String s) {
        super(s);
    }
}
