package pl.grizzlysoftware.developmentshowcase.web.resource


import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
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
class NumberResourceTest extends Specification {

    @LocalServerPort
    String port

    def "it should return number"() {
        given:
            def numberResource = service("http://localhost:${port}", NumberResourceService.class)
        when:
            def response = numberResource.getNumber().execute()
        then:
            response.isSuccessful()
            response.body().value != null //since it's pseudo-random we cannot be sure what is the output

    }
}
