package com.vustuntas.coinnest.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vustuntas.coinnest.model.CryptoModel
import com.vustuntas.coinnest.R
import java.util.Random

class CryptoAdapter(val listCryptoInfo:ArrayList<CryptoModel>) : RecyclerView.Adapter<CryptoAdapter.CryptoVH>() {
    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")
    class CryptoVH(itemView:View) : RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return CryptoVH(itemView)
    }

    override fun onBindViewHolder(holder: CryptoVH, position: Int) {
        val cryptoObject = listCryptoInfo.get(position) as CryptoModel
        holder.itemView.setBackgroundColor(Color.parseColor(colors.get(position % 8)))
        holder.itemView.findViewById<TextView>(R.id.recycler_row_currency).text = cryptoObject.currency
        holder.itemView.findViewById<TextView>(R.id.recycler_row_price).text = cryptoObject.price
    }

    override fun getItemCount(): Int {
        return listCryptoInfo.size
    }

}