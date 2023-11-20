package pl.grizzlysoftware.developmentshowcase.provider.java_random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider;

@Configuration
class JavaRandomBeansConfig {
    @Bean
    NumberProvider javaRandomNumberProvider() {
        return new JavaRandomNumberProvider();
    }

    @Bean
    NumberProvider failingJavaRandomNumberProvider() {
        return new FailingJavaRandomNumberProvider();
    }
}
