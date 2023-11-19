package pl.grizzlysoftware.developmentshowcase.provider.jpa


import spock.lang.Specification
import spock.lang.Unroll

import static AppNumber.of

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class JpaNumberProviderTest extends Specification {

    def "it should throw NullPointerException when given constructor arg is null"() {
        when:
            new JpaNumberProvider(null)
        then:
            thrown(NullPointerException)
    }

    @Unroll
    def "it should return one of the numbers provided by given repository"(numbers) {
        given:
            def repositoryMock = Mock(AppNumberRepository)
            repositoryMock.findAll() >> numbers
            def provider = new JpaNumberProvider(repositoryMock)
        when:
            def output = provider.next()
        then:
            numbers.stream().filter({ e -> e.value == output }).findAny()
        where:
            numbers << [
                    [of(1)],
                    [of(1), of(2)],
                    [of(1), of(2), of(3)],
                    [of(3), of(4), of(5)],
                    [of(6), of(7), of(8)]
            ]
    }

    def "it should use repository to get the number"() {
        given:
            def repositoryMock = Mock(AppNumberRepository)
            1 * repositoryMock.findAll() >> [of(1)]
            def provider = new JpaNumberProvider(repositoryMock)
        when:
            def output = provider.next()
        then:
            output != null
    }
}
