package com.santra.sanchita.interviewprepapp.data.network;

import android.content.Context;

import com.santra.sanchita.interviewprepapp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sanchita on 15/11/17.
 */

public class RestClient {
    private static Retrofit retrofit;

    private RestClient(){
    }

    public static Retrofit getInstance(Context mContext){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(RestClientSSL.getOkHttpClient(mContext))
                .build();
        /*retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(RestClientSSL.getOkHttpClientLocal(mContext))
            .build();*/
        return retrofit;
    }
}
