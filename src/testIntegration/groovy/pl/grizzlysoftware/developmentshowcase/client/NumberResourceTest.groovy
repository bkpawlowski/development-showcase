package pl.grizzlysoftware.developmentshowcase.client


import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static java.time.Duration.ofSeconds
import static org.awaitility.Awaitility.await
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
        invalidateCache()
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

    @Unroll
    def "/number/v1/batch/async - it should generate numbers asynchronously for #numbers"(Integer numbers) {
        given:
            def alreadyGeneratedNumbers = fetchGeneratedNumbers()
        when:
            def response = serviceV1.generateBatch(numbers).execute()
        then:
            response.isSuccessful()
        when:
            def generatedNumbersAfterBatch = fetchGeneratedNumbers()
        then: "size of generated numbers should be still the same as process is faulty and is executed within transaction that should rollback every saved generated number"
            await().atMost(ofSeconds(2))
                .until { alreadyGeneratedNumbers.size() == fetchGeneratedNumbers().size() }
        then:
            alreadyGeneratedNumbers.size() == generatedNumbersAfterBatch.size()
        where:
            numbers << [null, 0, 5, 10, 50]
    }

    void invalidateCache() {
        def response = serviceV1.invalidateCache().execute()
        assert response.isSuccessful()
    }

    Collection<GeneratedNumber> fetchGeneratedNumbers() {
        def response = serviceV1.findGeneratedNumbers().execute()
        assert response.isSuccessful()
        return response.body()
    }
}
