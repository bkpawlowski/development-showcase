package pl.grizzlysoftware.developmentshowcase.businesslogic.provider

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class SummingCompositeNumberProviderTest extends Specification {
    def "it should throw IllegalArgumentException if given NumberProviders collection is null"() {
        when:
            new SummingCompositeNumberProvider(null)
        then:
            thrown(IllegalArgumentException)
    }

    def "it should throw IllegalArgumentException if given NumberProviders collection is empty"() {
        when:
            new SummingCompositeNumberProvider([])
        then:
            thrown(IllegalArgumentException)
    }

    @Unroll
    def "it should sum numbers returned from all given NumberProviders"(providers, expectedSum) {
        given:
            def provider = new SummingCompositeNumberProvider(providers)
        when:
            def output = provider.getNumber()
        then:
            output == expectedSum
        where:
            providers                                        || expectedSum
            [p(1)]                                           || 1
            [p(0), p(1), p(3)]                               || 4
            [p(-10), p(1), p(3)]                             || -6
            [p(-12.1), p(1.1), p(15.331121)]                 || 4.331121
            [p(120_000_000_000_000_000_000_000_000), p(1.1)] || 120_000_000_000_000_000_000_000_001.1
    }

    def "p"(number) {
        return new InMemoryStaticNumberProvider(number)
    }

    private static class InMemoryStaticNumberProvider implements NumberProvider {
        private BigDecimal number

        InMemoryStaticNumberProvider(BigDecimal number) {
            this.number = number
        }

        @Override
        BigDecimal getNumber() {
            return number
        }
    }
}
