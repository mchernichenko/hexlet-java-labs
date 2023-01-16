package exercise.exception;

public class CustomException extends Exception {
    private static final String EXCEPTION_DESCRIPTION_FORMAT = "[%s]: %s";
    private String errorCode;
    private String message;

    public CustomException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorDescription() {
        return String.format(EXCEPTION_DESCRIPTION_FORMAT, this.errorCode, this.message);
    }
}
