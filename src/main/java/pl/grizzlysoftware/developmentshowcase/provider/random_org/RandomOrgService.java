package pl.grizzlysoftware.developmentshowcase.provider.random_org;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public interface RandomOrgService {
    @GET("integers/?num=1&col=1&base=10&format=plain&rnd=new")
    Call<Integer> randomInteger(@Query("min") Integer min, @Query("max") Integer max);
}
