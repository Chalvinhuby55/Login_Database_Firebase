package com.example.loginapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type

class wisataAdapter (var mContext: Context, var wisataList: List<ItemData>):

    RecyclerView.Adapter<wisataAdapter.ListViewHolder>() {

    inner class ListViewHolder(var v: View) : RecyclerView.ViewHolder(v) {

        val imgTT = v.findViewById<ImageView>(R.id.dataImg)

        val judulTT = v.findViewById<TextView>(R.id.tmptText)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wisataAdapter.ListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.data_data, parent, false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }


    override fun onBindViewHolder(holder: wisataAdapter.ListViewHolder, position: Int) {
        val newList = wisataList[position]
        holder.imgTT.loadImage(newList.imgurl)
        holder.judulTT.text = newList.judul
        holder.v.setOnClickListener {

            val desk = newList.deskripsi
            val judul = newList.judul
            val img = newList.imgurl

            val mIntent = Intent(mContext, Detail::class.java)
            mIntent.putExtra("DESKT", desk)
            mIntent.putExtra("JUDULT", judul)
            mIntent.putExtra("IMGT", img)
            mContext.startActivity(mIntent)
        }
    }


}