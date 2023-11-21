package pl.grizzlysoftware.developmentshowcase;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.springframework.boot.SpringApplication.run;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
@SpringBootApplication
@EnableRetry
@EnableAsync
@EnableCaching
public class ApplicationStarter {
    public static void main(String[] args) {
        final var applicationCtx = run(ApplicationStarter.class, args);

    }
}
