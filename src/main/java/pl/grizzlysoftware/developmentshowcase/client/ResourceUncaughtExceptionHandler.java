package pl.grizzlysoftware.developmentshowcase.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.grizzlysoftware.developmentshowcase.domain.GetNumberException;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@ControllerAdvice
@Slf4j
class ResourceUncaughtExceptionHandler {

    @ExceptionHandler(GetNumberException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleGetNumberException(GetNumberException e) {
        log.warn("Uncaught exception while fetching numbers: {}", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        log.warn("Uncaught exception while handling request: {}", e.getMessage());
    }
}
