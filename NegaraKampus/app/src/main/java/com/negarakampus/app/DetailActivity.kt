package com.negarakampus.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.negarakampus.app.adapter.KampusListAdapter
import com.negarakampus.app.viewmodel.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {
    lateinit var recylerviewAdapter:KampusListAdapter

    companion object {

        const val EXTRA_KIRIMAN_HOME = "extra_kiriman_home"



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val country = intent.getStringExtra(EXTRA_KIRIMAN_HOME)

        if (country != "extra_kiriman_home" && country != "" && country != null){

            initRecylerView()
            initViewModel(country)
        }
    }
    private fun initRecylerView(){
        kampusListRecylerview.layoutManager = LinearLayoutManager(this)
        recylerviewAdapter = KampusListAdapter(this)
        kampusListRecylerview.adapter =recylerviewAdapter
    }

    private fun initViewModel(country: String) {

        val viewModel: DetailActivityViewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)

        viewModel.getLiveDataObserver().observe(this, Observer {
            println("dataaa" + it)
            if (it != null){
                recylerviewAdapter.setKampusList(it)
                recylerviewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(country)
    }


}