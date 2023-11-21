package pl.grizzlysoftware.developmentshowcase.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.grizzlysoftware.developmentshowcase.domain.GenerateNumberException;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@Slf4j
@ControllerAdvice
class ResourceUncaughtExceptionHandler {

    @ExceptionHandler(GenerateNumberException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleGenerateNumberException(GenerateNumberException e) {
        log.warn("Uncaught exception while generating number: {}", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        log.warn("Uncaught exception while handling request: {}", e.getMessage());
    }
}
