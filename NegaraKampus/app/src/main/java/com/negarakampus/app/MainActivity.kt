package com.negarakampus.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.negarakampus.app.R.*
import com.negarakampus.app.adapter.CountryListAdapter
import com.negarakampus.app.data.CountryModel
import com.negarakampus.app.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var recylerviewAdapter:CountryListAdapter

    private var country: ArrayList<CountryModel>? = null
    private var tempCountry: ArrayList<CountryModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        initRecylerView()
        initViewModel()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                tempCountry?.clear()
                val searchText = p0!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    country?.forEach{
                        if (it.name!!.toLowerCase(Locale.getDefault()).contains(searchText)){
                            tempCountry?.add(it)
                        }
                    }
                    recylerviewAdapter.setCountryList(tempCountry)
                    recylerviewAdapter.notifyDataSetChanged()
                }else{
                    tempCountry?.clear()
                    country?.let { tempCountry?.addAll(it) }
                    recylerviewAdapter.setCountryList(tempCountry)
                    recylerviewAdapter.notifyDataSetChanged()
                }
              return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun initRecylerView(){
        countryListRecylerview.layoutManager = LinearLayoutManager(this)
        recylerviewAdapter = CountryListAdapter(this)
        countryListRecylerview.adapter =recylerviewAdapter
    }

    private fun initViewModel(){

        val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getLiveDataObserver().observe(this, Observer {

            if (it != null){
                recylerviewAdapter.setCountryList(it)
                recylerviewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }


}

