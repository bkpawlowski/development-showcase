package pl.grizzlysoftware.developmentshowcase.web.mapper


import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class BigDecimalToNumberDtoMapperTest extends Specification {
    def "it should throw IllegalArgumentException when given input is null"() {
        when:
            new BigDecimalToNumberDtoMapper().apply(null)
        then:
            thrown(IllegalArgumentException)
    }

    @Unroll
    def "it should map given input to value"(input) {
        given:
            def mapper = new BigDecimalToNumberDtoMapper()
        when:
            def output = mapper.apply(input)
        then:
            output != null
            output.value == input
        where:
            input << [-1, 0, 1, 500, 100_000_000_000]
    }
}
