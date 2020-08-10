package pl.grizzlysoftware.developmentshowcase.web.resource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
import pl.grizzlysoftware.developmentshowcase.businesslogic.exception.GetNumberException
import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.NumberProvider
import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.SummingCompositeNumberProvider
import spock.lang.Specification

import static pl.grizzlysoftware.developmentshowcase.util.RetrofitUtils.service

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@DevelopmentShowCaseIntegrationTest
@Sql(scripts = [
        "classpath:/database-init/clear_data.sql",
        "classpath:/database-init/t_number_data.sql",
])
class NumberResourceExceptionHandlingTest extends Specification {

    @Autowired
    SummingCompositeNumberProvider numberProvider

    @LocalServerPort
    String port

    def "it should return 503 when any of the providers throws exception while executing getNumber"() {
        given:
            numberProvider.numberProviders.add(new ExceptionThrowingNumberProvider());
            def numberResource = service("http://localhost:${port}", NumberResourceService.class)
        when:
            def response = numberResource.getNumber().execute()
        then:
            !response.isSuccessful()
            response.code() == HttpStatus.SERVICE_UNAVAILABLE.value()
    }

    private static class ExceptionThrowingNumberProvider implements NumberProvider {
        @Override
        BigDecimal getNumber() {
            throw new GetNumberException("This is desired behaviour")
        }
    }
}
