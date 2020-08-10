package pl.grizzlysoftware.developmentshowcase.web.resource

import pl.grizzlysoftware.developmentshowcase.web.dto.NumberDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
interface NumberResourceService {
    @GET("number/v1/random")
    Call<NumberDto> getNumber()
}
