package pl.grizzlysoftware.developmentshowcase.provider.random_org

import okhttp3.MediaType
import okhttp3.ResponseBody
import pl.grizzlysoftware.developmentshowcase.businesslogic.exception.GetNumberException
import retrofit2.Call
import retrofit2.Response
import spock.lang.Specification

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class RandomOrgNumberProviderTest extends Specification {

    def "it should throw NullPointerException when given constructor arg is null"() {
        when:
            new RandomOrgNumberProvider(null)
        then:
            thrown(NullPointerException)
    }

    def "it should return BigDecimal number"() {
        given:
            def callMock = Mock(Call) {
                execute() >> Response.success(200, 5)
            }
            def serviceMock = Mock(RandomOrgService) {
                randomInteger(_, _) >> callMock
            }
            def provider = new RandomOrgNumberProvider(serviceMock)
        when:
            def output = provider.getNumber()
        then:
            output != null
    }

    def "it should use RandomOrgService to get the number"() {
        given:
            def callMock = Mock(Call) {
                execute() >> Response.success(200, 5)
            }
            def serviceMock = Mock(RandomOrgService) {
                1 * randomInteger(_, _) >> callMock
            }
            def provider = new RandomOrgNumberProvider(serviceMock)
        when:
            provider.getNumber()
        then:
            noExceptionThrown()
    }

    def "it should throw GetNumberException if IOException is thrown during getting number from RandomOrgService"() {
        given:
            def callMock = Mock(Call) {
                execute() >> {
                    throw new IOException("mock exception")
                }
            }
            def serviceMock = Mock(RandomOrgService)
            serviceMock.randomInteger(_, _) >> callMock
            def provider = new RandomOrgNumberProvider(serviceMock)
        when:
            provider.getNumber()
        then:
            thrown(GetNumberException)
    }

    def "it should throw GetNumberException if executed service did not returned success response"() {
        given:
            def callMock = Mock(Call) {
                execute() >> Response.error(503, ResponseBody.create(MediaType.parse("text/plain"), new byte[0]))
            }
            def serviceMock = Mock(RandomOrgService)
            serviceMock.randomInteger(_, _) >> callMock
            def provider = new RandomOrgNumberProvider(serviceMock)
        when:
            provider.getNumber()
        then:
            thrown(GetNumberException)
    }
}
