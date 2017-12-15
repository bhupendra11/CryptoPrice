package com.bhupendra.cryptoprice.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bhupendra Shekhawat on 12/15/2017.
 */

public class ZebPayAPIClient {

    private static final String BASE_URL = "https://www.zebapi.com/api/v1/market/ticker/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
