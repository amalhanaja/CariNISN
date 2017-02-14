package com.doy.nisn.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by doy on 2/14/17.
 */

public class ApiClient {

    //http://ibacor.com/api/data-siswa?nisn=9985456034&k=459d54c52c55f588d23fd40abcd5784c
    public static final String BASE_URL = "http://ibacor.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
