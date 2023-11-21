package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumberPersister;
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumberProvider;
import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider;

@Configuration
public class JpaProviderBeansConfig {
    @Bean
    NumberProvider persistenceRandomNumberProvider(AppNumberRepository repository) {
        return new JpaNumberProvider(repository);
    }

    @Bean
    GeneratedNumberPersister generatedNumberPersister(GeneratedNumberRepository generatedNumberRepository) {
        return new JpaGeneratedNumberPersister(generatedNumberRepository);
    }

    @Bean
    GeneratedNumberProvider jpaGeneratedNumberProvider(GeneratedNumberRepository generatedNumberRepository) {
        return new JpaGeneratedNumberProvider(generatedNumberRepository);
    }
}
