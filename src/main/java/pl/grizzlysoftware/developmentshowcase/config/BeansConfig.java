package pl.grizzlysoftware.developmentshowcase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.NumberProvider;
import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.SummingCompositeNumberProvider;
import pl.grizzlysoftware.developmentshowcase.provider.java_random.JavaRandomNumberProvider;
import pl.grizzlysoftware.developmentshowcase.provider.persistence.PersistenceNumberProvider;
import pl.grizzlysoftware.developmentshowcase.provider.random_org.RandomOrgNumberProvider;
import pl.grizzlysoftware.developmentshowcase.provider.random_org.RandomOrgService;
import pl.grizzlysoftware.developmentshowcase.repository.AppNumberRepository;

import java.util.Collection;

import static pl.grizzlysoftware.developmentshowcase.util.RetrofitUtils.service;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@Configuration
public class BeansConfig {
    @Bean
    NumberProvider randomOrgNumberProvider(@Value("${provider.number.random_org.service.host}") String host) {
        var randomOrgService = service(host, RandomOrgService.class);
        return new RandomOrgNumberProvider(randomOrgService);
    }

    @Bean
    NumberProvider javaRandomNumberProvider() {
        return new JavaRandomNumberProvider();
    }

    @Bean
    @Autowired
    NumberProvider persistenceRandomNumberProvider(AppNumberRepository repository) {
        return new PersistenceNumberProvider(repository);
    }

    @Bean
    @Autowired
    @Primary
    NumberProvider summingCompositeNumberProvider(Collection<NumberProvider> numberProviders) {
        return new SummingCompositeNumberProvider(numberProviders);
    }

}
