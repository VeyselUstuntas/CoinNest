package com.vustuntas.coinnest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.vustuntas.coinnest.R
import com.vustuntas.coinnest.adapter.CryptoAdapter
import com.vustuntas.coinnest.model.CryptoModel
import com.vustuntas.coinnest.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModelArrayList : ArrayList<CryptoModel>? = null
    private lateinit var recyclerView:RecyclerView
    private lateinit var compositeDisposable : CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = RecyclerView(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        compositeDisposable = CompositeDisposable()

        loadData()

    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CryptoAPI::class.java)


        compositeDisposable.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )



        /*
        call.enqueue(object:Callback<List<CryptoModel>>{
            val service = retrofit.create(CryptoAPI::class.java)

            val call = service.getData()

            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>){
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModelArrayList = ArrayList<CryptoModel>(it)
                    }

                    cryptoModelArrayList?.let {
                        recyclerView.adapter = CryptoAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                println(t.localizedMessage)
            }
        })
        */
    }


    private fun handleResponse(cryptoList:List<CryptoModel>){
        cryptoModelArrayList = ArrayList(cryptoList)

        cryptoModelArrayList?.let {
            recyclerView.adapter = CryptoAdapter(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
