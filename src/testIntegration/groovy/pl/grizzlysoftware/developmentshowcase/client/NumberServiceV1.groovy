package pl.grizzlysoftware.developmentshowcase.client

import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
interface NumberServiceV1 {
    @GET("number/v1/random")
    Call<SimpleNumber> generateNumber()


    @GET("number/v1/generated")
    Call<Collection<GeneratedNumber>> findGeneratedNumbers()
}
