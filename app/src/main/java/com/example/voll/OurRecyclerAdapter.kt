package com.example.voll

import android.content.Context
import android.content.Intent
import android.provider.Telephony.Mms.Sent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class OurRecyclerAdapter(val context: Context, val elements: List<Posts>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<OurRecyclerAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(position: Int)
    }

    inner class  ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.uuid)
        val container = view.findViewById<LinearLayout>(R.id.uubox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recy_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return elements.size

    }


    //    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text = elements[position].title
        if(position%2 == 0){
            holder.textView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary))
        }
        else {
            holder.textView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent))
        }
        holder.container.setOnClickListener{
            itemClickListener.onItemClick(position)
//            vollViewModel.index = position
//
//            Log.d("hehe", "index is ${vollViewModel.index}")
//            Log.d("hehe", "pos is $position")
//            notifyDataSetChanged()
//            it.findNavController().navigate(R.id.informationFragment,)
        }
    }

}