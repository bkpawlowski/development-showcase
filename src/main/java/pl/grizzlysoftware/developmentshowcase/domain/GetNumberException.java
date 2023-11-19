package pl.grizzlysoftware.developmentshowcase.domain;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class GetNumberException extends RuntimeException {
    public GetNumberException(String message) {
        super(message);
    }

    public GetNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
