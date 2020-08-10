package pl.grizzlysoftware.developmentshowcase.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.grizzlysoftware.developmentshowcase.businesslogic.exception.GetNumberException;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@ControllerAdvice
public class ResourceUncaughtExceptionHandler {

    @ExceptionHandler(GetNumberException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleGetNumberException() {

    }
}
