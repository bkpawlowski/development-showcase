package pl.grizzlysoftware.developmentshowcase;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
@SpringBootApplication
public class ApplicationStarter {
    public static void main(String[] args) {
        run(ApplicationStarter.class, args);
    }
}
