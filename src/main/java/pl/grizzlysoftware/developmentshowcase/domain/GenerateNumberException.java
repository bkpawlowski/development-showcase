package pl.grizzlysoftware.developmentshowcase.domain;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class GenerateNumberException extends RuntimeException {
    public GenerateNumberException(String message) {
        super(message);
    }

    public GenerateNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
