package pl.grizzlysoftware.developmentshowcase.client


import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
import spock.lang.Shared
import spock.lang.Specification

import static pl.grizzlysoftware.developmentshowcase.util.RetrofitUtils.service

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@DevelopmentShowCaseIntegrationTest
@Sql(scripts = [
        "classpath:/database-init/clear_data.sql",
        "classpath:/database-init/prepare_data.sql",
])
class NumberResourceTest extends Specification {

    @LocalServerPort
    String port

    @Shared
    NumberServiceV1 serviceV1

    def setup() {
        serviceV1 = service("http://localhost:${port}", NumberServiceV1.class)
    }

    def "/number/v1/random - it should generate random number"() {
        when:
            def response = serviceV1.generateNumber().execute()
        then:
            response.isSuccessful()
            response.body().value != null //since it's pseudo-random we cannot be sure what is the output
    }

    def "/number/v1/generated - it should return previously generated numbers"() {
        when:
            def response = serviceV1.findGeneratedNumbers().execute()
        then:
            response.isSuccessful()
            response.body().value != null //since it's pseudo-random we cannot be sure what is the output

    }
}