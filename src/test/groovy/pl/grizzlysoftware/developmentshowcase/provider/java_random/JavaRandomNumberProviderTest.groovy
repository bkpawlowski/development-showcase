package pl.grizzlysoftware.developmentshowcase.provider.java_random

import spock.lang.Specification

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class JavaRandomNumberProviderTest extends Specification {
    def "it should return a BigDecimal value"() {
        given:
            def numberProvider = new JavaRandomNumberProvider()
        when:
            def output = numberProvider.getNumber()
        then:
            output != null
    }
}
