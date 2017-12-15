package com.bhupendra.cryptoprice.rest;

import com.bhupendra.cryptoprice.model.ZebPayAPIModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bhupendra Shekhawat on 12/15/2017.
 */

public interface ZebPayAPIInterface {

    @GET("btc/inr")
    Call<ZebPayAPIModel> getZebPayTicker();
}
