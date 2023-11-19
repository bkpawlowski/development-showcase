package pl.grizzlysoftware.developmentshowcase.provider.random_org;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider;

import static pl.grizzlysoftware.developmentshowcase.util.RetrofitUtils.service;

@Configuration
class RandomOrgBeansConfig {
    @Bean
    NumberProvider randomOrgNumberProvider(@Value("${provider.number.random_org.service.host}") String host) {
        var randomOrgService = service(host, RandomOrgService.class);
        return new RandomOrgNumberProvider(randomOrgService);
    }
}
