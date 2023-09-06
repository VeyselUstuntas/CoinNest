package com.vustuntas.coinnest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CryptoModel (
    //@SerializedName("currency")
    val currency : String,

    //@SerializedName("price")
    val price:String

    ) :Serializable