package pl.grizzlysoftware.developmentshowcase.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import pl.grizzlysoftware.developmentshowcase.DevelopmentShowCaseIntegrationTest
import spock.lang.Specification

@DevelopmentShowCaseIntegrationTest
@Sql(scripts = [
    "classpath:/database-init/clear_data.sql",
    "classpath:/database-init/prepare_data.sql",
])
class GeneratedNumberFinderTest extends Specification {

    GeneratedNumberFinder finder

    NumberGenerator numberGenerator

    def setup() {
        finder.invalidateCache()
    }

    def "findAll - it should hit cache after first invocation"() {
        when:
            def firstInvocationResults = finder.findAll()
        then: "it should return non-empty results"
            !firstInvocationResults.isEmpty()
        when: "generating new number - side effect is persisting it"
            numberGenerator.next()
        and: "retrieving generated number"
            def secondInvocationResults = finder.findAll().sort()
            def thirdInvocationResults = finder.findAll().sort()
        then: "results should be the same - because of cache hit"
            firstInvocationResults == secondInvocationResults
            secondInvocationResults == thirdInvocationResults
        when: "invalidating cache"
            finder.invalidateCache()
        and: "retrieving generated numbers again"
            def postCacheInvalidationInvocationResult = finder.findAll().sort()
        then: "it should return non-empty results"
            !postCacheInvalidationInvocationResult.isEmpty()
        then: "it should omit cache and retrieve actual state of application"
            firstInvocationResults.size() != postCacheInvalidationInvocationResult.size()
    }

    @Autowired
    void setFinder(GeneratedNumberFinder finder) {
        this.finder = finder
    }

    @Autowired
    void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator
    }
}
