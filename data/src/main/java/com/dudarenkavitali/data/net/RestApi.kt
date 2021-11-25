package com.dudarenkavitali.data.net

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("feed.js")
    fun getFeeds(@Query("faa") faa: Int,
                 @Query("bounds") bounds: String,
                 @Query("satellite") satellite: Int,
                 @Query("mlat") mlat: Int,
                 @Query("flarm") flarm: Int,
                 @Query("adsb") adsb: Int,
                 @Query("gnd") gnd: Int,
                 @Query("air") air: Int,
                 @Query("vehicles") vehicles: Int,
                 @Query("estimated") estimated: Int,
                 @Query("maxage") maxage: Int,
                 @Query("gliders") gliders: Int,
                 @Query("stats") stats: Int): Single<Map<String, Any>>

}