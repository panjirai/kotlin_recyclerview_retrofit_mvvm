package com.negarakampus.app.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.negarakampus.app.DetailActivity
import com.negarakampus.app.MainActivity
import com.negarakampus.app.R
import com.negarakampus.app.data.CountryModel
import kotlinx.android.synthetic.main.country_list_row.view.*

class CountryListAdapter(val activity: Activity): RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {
    private var countryList: List<CountryModel>? = null
    private var countryListFilter: List<CountryModel>? = null



    fun setCountryList(countryList: List<CountryModel>?){
        this.countryList = countryList
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
       holder.bind(countryList?.get(position)!!, activity)

    }

    override fun getItemCount(): Int {
        if (countryList == null) return  0
        else return countryList?.size!!

    }



    class MyViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        val flagImage = view.flagImage
        val tvName = view.tVNameCountry
        val tVCapital = view.tVCapitalCountry
        val tVRegion = view.tVRegionCountry

        fun bind(data : CountryModel, activity: Activity){

            tvName.text = data.name
            tVCapital.text = "Capital : " + data.capital
            tVRegion.text = "Region : " + data.region

            GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag),flagImage)

            itemView.setOnClickListener{
                val act = Intent(itemView.context, DetailActivity::class.java)
                act.putExtra(DetailActivity.EXTRA_KIRIMAN_HOME, data.name)

                itemView.context.startActivity(act)

            }



        }


    }


}

