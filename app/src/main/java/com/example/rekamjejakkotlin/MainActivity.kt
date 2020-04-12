package com.example.rekamjejakkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rekamjejakkotlin.viewmodel.CountryState
import com.example.rekamjejakkotlin.viewmodel.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel : MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var date : Date = Calendar.getInstance().time
        tv_date.text = date.toString()
        mainViewModel = ViewModelProviders.of(this).get(MainView::class.java)
        mainViewModel.fetchData()
        mainViewModel.getCountry().observe(this, Observer {
            tv_count_positif.text = it.get(0).meninggal
            tv_count_positif.text = it.get(0).positif
            tv_count_sembuh.text = it.get(0).sembuh
        })


        mainViewModel.getState().observe(this, Observer {
            handleUIState(it)
        })
    }

    private fun handleUIState(it : CountryState){
        when(it){
            is CountryState.IsLoading -> isLoading(it.state)
            is CountryState.Error -> {
                toast(it.err!!)
                shimmer.startShimmer()
            }
        }
    }

    private fun toast(message : String) = Toast.makeText(this,message,Toast.LENGTH_LONG).show()

    private fun isLoading(state : Boolean){
        if(state){
            shimmer.startShimmer()
        }else{
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
            base.visibility = View.VISIBLE
        }
    }

}
