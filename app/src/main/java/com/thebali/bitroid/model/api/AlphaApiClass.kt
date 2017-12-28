package com.thebali.bitroid.model.api

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by thebali on 23/12/17.
 */
class AlphaApiClass {

    val service1: AlphaApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.alphavantage.co/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build()).build()

        service1 = retrofit.create(AlphaApi::class.java)
    }

    fun exchange(a: String, b: String, c: String): Observable<RealtimeCurrencyExchangeRate> {

        return service1.exchange(a, b, c).flatMap { dataResult ->
            Observable.just(dataResult.realtimeCurrencyExchangeRate)
        }.map { output ->
            RealtimeCurrencyExchangeRate(
                    output.fromCurrencyCode,
                    output.fromCurrencyName,
                    output.toCurrencyCode,
                    output.toCurrencyName,
                    output.exchangeRate,
                    output.lastRefreshed,
                    output.timeZone)
        }

    }


}