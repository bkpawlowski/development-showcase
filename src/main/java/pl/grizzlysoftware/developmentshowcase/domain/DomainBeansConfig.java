package pl.grizzlysoftware.developmentshowcase.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@Configuration
class DomainBeansConfig {

    @Bean
    SummingCompositeNumberProvider summingCompositeNumberProvider(Collection<NumberProvider> numberProviders) {
        return new SummingCompositeNumberProvider(numberProviders);
    }

    @Bean
    GeneratedNumberFinder generatedNumberFinder(Collection<GeneratedNumberProvider> numberProviders) {
        return new GeneratedNumberFinder(numberProviders);
    }

    @Bean
    NumberGenerator numberGenerator(SummingCompositeNumberProvider summingCompositeNumberProvider, GeneratedNumberPersister generatedNumberPersister) {
        return new NumberGenerator(summingCompositeNumberProvider, generatedNumberPersister);
    }

}
