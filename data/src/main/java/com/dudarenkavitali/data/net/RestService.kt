package com.dudarenkavitali.data.net

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService(private val apiUrl: String) {

    private val restApi: RestApi
    private val restParser: RestErrorParser

    init {
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        val gson = GsonBuilder()
            .create()
        restParser = RestErrorParser(gson)
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()
        restApi = retrofit.create(RestApi::class.java)
    }

    fun getFeeds(
        faa: Int,
        bounds: String,
        satellite: Int,
        mlat: Int,
        flarm: Int,
        adsb: Int,
        gnd: Int,
        air: Int,
        vehicles: Int,
        estimated: Int,
        maxage: Int,
        gliders: Int,
        stats: Int
    ): Single<Map<String, Any>> {
        return restApi.getFeeds(
            faa,
            bounds,
            satellite,
            mlat,
            flarm,
            adsb,
            gnd,
            air,
            vehicles,
            estimated,
            maxage,
            gliders,
            stats, System.currentTimeMillis()
        ).compose(restParser.parseError())
    }

}