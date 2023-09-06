package com.vustuntas.coinnest.service

import com.vustuntas.coinnest.model.CryptoModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //https://raw.githubusercontent.com/VeyselUstuntas/CoinNest/main/crypto.json
    @GET("VeyselUstuntas/CoinNest/main/crypto.json")
    fun getData() : Flowable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>
}