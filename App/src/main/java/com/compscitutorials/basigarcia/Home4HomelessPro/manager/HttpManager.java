package com.compscitutorials.basigarcia.Home4HomelessPro.manager;

import android.content.Context;

import com.compscitutorials.basigarcia.Home4HomelessPro.app.Contextor;
import com.compscitutorials.basigarcia.Home4HomelessPro.service.IHome4Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    public static HttpManager httpsManager;

    public static HttpManager getInstance() {
        if (httpsManager == null)
            httpsManager = new HttpManager();

        return httpsManager;
    }

    private IHome4Service service;
    private Context mContext;

    private HttpManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        mContext = Contextor.getInstance().getContext();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://home4homeless.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        service = retrofit.create(IHome4Service.class);
    }

    public IHome4Service getService() {
        return service;
    }
}
