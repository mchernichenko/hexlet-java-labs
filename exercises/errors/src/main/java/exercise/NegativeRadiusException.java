package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    private String errorCode;

    public NegativeRadiusException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
// END
