package com.bhupendra.cryptoprice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhupendra Shekhawat on 12/14/2017.
 */

public class ZebPayAPIModel {

    @SerializedName("market")
    private long market;

    @SerializedName("buy")
    private long buy;

    @SerializedName("sell")
    private long sell;

    @SerializedName("currency")
    private String currency;

    @SerializedName("volume" )
    private double volume;


    public long getMarket() {
        return market;
    }

    public long getBuy() {
        return buy;
    }

    public long getSell() {
        return sell;
    }

    public String getCurrency() {
        return currency;
    }

    public double getVolume() {
        return volume;
    }
}
