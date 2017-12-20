package com.thebali.greatone

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface AlphaApi {

    @GET("query")
    fun hitCountCheck(@Query("function") function1: String,
                      @Query("symbol") symbol1: String,
                      @Query("market") market1: String,
                      @Query("apikey") apikey1: String): Observable<Model.Result>

    companion object {
        fun create(): AlphaApi {

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.alphavantage.co/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(AlphaApi::class.java)
        }
    }

}