package com.negarakampus.app.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.negarakampus.app.DetailActivity
import com.negarakampus.app.R
import com.negarakampus.app.data.KampusModel
import kotlinx.android.synthetic.main.country_list_row.view.*
import kotlinx.android.synthetic.main.country_list_row.view.tVRegionCountry
import kotlinx.android.synthetic.main.kampus_list_row.view.*
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity







class KampusListAdapter(val activity: Activity): RecyclerView.Adapter<KampusListAdapter.MyViewHolder>() ,
    Filterable {
    private var kampusList: List<KampusModel>? = null
    private var countryListFilter: List<KampusModel>? = null



    fun setKampusList(kampusList: List<KampusModel>?){
        this.kampusList = kampusList
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KampusListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kampus_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: KampusListAdapter.MyViewHolder, position: Int) {
        holder.bind(kampusList?.get(position)!!, activity)

    }

    override fun getItemCount(): Int {
        if (kampusList == null) return  0
        else return kampusList?.size!!

    }

    class MyViewHolder(view:View) : RecyclerView.ViewHolder(view) {


        val tvName = view.tVNameKampus
        val tvWeb = view.tVWebPages
        val tVCode = view.tVCode

        fun bind(data: KampusModel, activity: Activity){

            tvName.text = data.name
            tVCode.text = data.country
            tvWeb.text = data.web_pages[0]
            itemView.setOnClickListener{
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(tvWeb.toString())
                itemView.context.startActivity(openURL)

            }




        }



    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


}