package vehiclerental;

public class UserNameIsAlreadyTakenException extends RuntimeException{
    public UserNameIsAlreadyTakenException(String message) {
        super(message);
    }

    public UserNameIsAlreadyTakenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
