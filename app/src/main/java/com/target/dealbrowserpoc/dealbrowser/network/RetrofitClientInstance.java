package com.target.dealbrowserpoc.dealbrowser.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Network Manager to get make server communications.
 *  Retrofit instance to create a Network API request/response
 */
public class RetrofitClientInstance {
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://target-deals.herokuapp.com";


    public static Retrofit getRetrofitInstance(String URL) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
