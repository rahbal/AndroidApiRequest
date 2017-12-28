package com.thebali.bitroid.model.api

import com.thebali.bitroid.R
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaApi {

    @GET("query?&apikey=" + R.string.av_key)
    fun exchange(
            @Query("function") function: String,  //CURRENCY_EXCHANGE_RATE
            @Query("from_currency") from_currency: String,  // USD
            @Query("to_currency") to_currency: String  // INR
    ): Observable<Model1>

    /*




     */


}