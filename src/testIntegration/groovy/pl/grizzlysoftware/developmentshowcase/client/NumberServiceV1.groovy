package pl.grizzlysoftware.developmentshowcase.client

import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
interface NumberServiceV1 {
    @GET("number/v1/random")
    Call<SimpleNumber> generateNumber()

    @GET("number/v1/generated")
    Call<Collection<GeneratedNumber>> findGeneratedNumbers()

    @POST("number/v1/batch/async")
    Call<Void> generateBatch(@Query("numbersToGenerate") Integer numbersToGenerate)

    @POST("number/v1/cache/invalidate")
    Call<Void> invalidateCache()
}
