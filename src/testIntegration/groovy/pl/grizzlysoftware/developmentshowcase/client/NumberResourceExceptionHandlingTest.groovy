package pl.grizzlysoftware.developmentshowcase.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
import pl.grizzlysoftware.developmentshowcase.domain.GenerateNumberException
import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider
import pl.grizzlysoftware.developmentshowcase.domain.SummingCompositeNumberProvider
import spock.lang.Specification

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
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

    SummingCompositeNumberProvider numberProvider

    ExceptionThrowingNumberProvider exceptionThrowingNumberProvider

    String port

    def setup() {
        exceptionThrowingNumberProvider = new ExceptionThrowingNumberProvider()
        //nobody should ever modify bean state(state should be unmodifiable) i left this just for techflash purposes
        numberProvider.numberProviders.add(exceptionThrowingNumberProvider)
    }

    def cleanup() {
        numberProvider.numberProviders.remove(exceptionThrowingNumberProvider)
    }

    def "it should return 500 when any exception is thrown during processing"() {
        given:
            def numberResource = service("http://localhost:${port}", NumberServiceV1.class)
        when:
            def response = numberResource.generateNumber().execute()
        then:
            !response.isSuccessful()
            INTERNAL_SERVER_ERROR.value() == response.code()
    }

    private static class ExceptionThrowingNumberProvider implements NumberProvider {
        @Override
        BigDecimal next() {
            throw new GenerateNumberException("This is desired behaviour")
        }
    }

    @Autowired
    void setNumberProvider(SummingCompositeNumberProvider numberProvider) {
        this.numberProvider = numberProvider
    }

    @LocalServerPort
    void setPort(String port) {
        this.port = port
    }
}
