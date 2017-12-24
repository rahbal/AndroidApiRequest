package com.thebali.greatone.model.api

import com.google.gson.annotations.SerializedName


/**
 * Created by thebali on 22/12/17.
 */

data class Model1(
        @SerializedName("Realtime Currency Exchange Rate") val realtimeCurrencyExchangeRate:
        RealtimeCurrencyExchangeRate = RealtimeCurrencyExchangeRate()
)

data class RealtimeCurrencyExchangeRate(
        @SerializedName("1. From_Currency Code") val fromCurrencyCode: String = "", //USD
        @SerializedName("2. From_Currency Name") val fromCurrencyName: String = "", //United States Dollar
        @SerializedName("3. To_Currency Code") val toCurrencyCode: String = "", //INR
        @SerializedName("4. To_Currency Name") val toCurrencyName: String = "", //Indian Rupee
        @SerializedName("5. Exchange Rate") val exchangeRate: String = "", //64.04000000
        @SerializedName("6. Last Refreshed") val lastRefreshed: String = "", //2017-12-22 06:45:38
        @SerializedName("7. Time Zone") val timeZone: String = "" //UTC
)

