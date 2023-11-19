package pl.grizzlysoftware.developmentshowcase.provider.jpa


import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class AppNumberTest extends Specification {

    @Unroll
    def "it should create new instance from given argument"(BigDecimal number) {
        when:
            def output = new AppNumber(number)
        then:
            output != null
            output.value == number
        where:
            number << [-1, 0, 1, 2, 3.23131]
    }

    @Unroll
    def "of - it should return new instance from given argument"(double number) {
        when:
            def output = AppNumber.of(number)
        then:
            output != null
            output.value == number
        where:
            number << [-1, 0, 1, 2, 3.23131]
    }
}
