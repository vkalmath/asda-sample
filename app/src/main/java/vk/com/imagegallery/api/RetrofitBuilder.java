package vk.com.imagegallery.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class RetrofitBuilder {
    public static final String BASE_URL = "https://api.imgur.com/";
    private Retrofit retrofit;
    private static final RetrofitBuilder ourInstance = new RetrofitBuilder();

    public static synchronized RetrofitBuilder getInstance() {
        return ourInstance;
    }

    private RetrofitBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
