package pl.grizzlysoftware.developmentshowcase.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class RetrofitUtils {
    public static Retrofit retrofit(String url) {
        if (url != null && !url.endsWith("/")) {
            url += "/";
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

    public static <T> T service(String host, Class<T> clazz) {
        return retrofit(host)
                .create(clazz);
    }
}
