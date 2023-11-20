package pl.grizzlysoftware.developmentshowcase.config;

import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collection;

import static java.util.stream.Collectors.toMap;
import static org.springframework.boot.actuate.health.CompositeHealthContributor.fromMap;

/**
 * Created by Bartosz Pawłowski on 11/08/2020.
 */
@Configuration
class HealthConfig {

    @Bean
    HealthContributor h2Health(Collection<DataSource> dataSources) {
        return fromMap(dataSources.stream().collect(toMap(Object::toString, DataSourceHealthIndicator::new)));
    }
}
