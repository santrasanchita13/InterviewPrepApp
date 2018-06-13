package com.santra.sanchita.interviewprepapp.data.network;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sanchita on 6/12/17.
 */

public class RetrofitInstance {

    private RetrofitInstance() {
    }

    public static Retrofit getInstance(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(RestClientSSL.getOkHttpClientLocal(context))
                .build();
        return retrofit;
    }

}
