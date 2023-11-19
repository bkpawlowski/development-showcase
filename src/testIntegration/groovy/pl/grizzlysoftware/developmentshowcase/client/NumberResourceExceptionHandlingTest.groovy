package pl.grizzlysoftware.developmentshowcase.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
import pl.grizzlysoftware.developmentshowcase.domain.GetNumberException
import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider
import pl.grizzlysoftware.developmentshowcase.domain.SummingCompositeNumberProvider
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
class NumberResourceExceptionHandlingTest extends Specification {

    @Autowired
    SummingCompositeNumberProvider numberProvider

    @LocalServerPort
    String port

    def "it should return 503 when any of the providers throws exception while executing getNumber"() {
        given:
            numberProvider.numberProviders.add(new ExceptionThrowingNumberProvider());
            def numberResource = service("http://localhost:${port}", NumberServiceV1.class)
        when:
            def response = numberResource.generateNumber().execute()
        then:
            !response.isSuccessful()
            response.code() == HttpStatus.SERVICE_UNAVAILABLE.value()
    }

    private static class ExceptionThrowingNumberProvider implements NumberProvider {
        @Override
        BigDecimal next() {
            throw new GetNumberException("This is desired behaviour")
        }
    }
}
