package com.example.rekamjejakkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rekamjejakkotlin.model.ResponseCountry
import com.example.rekamjejakkotlin.network.Client
import com.example.rekamjejakkotlin.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainView : ViewModel() {

    private var country = MutableLiveData<List<ResponseCountry>>()
    private var state : SingleLiveEvent<CountryState> = SingleLiveEvent()
    private var api = Client.getInstance()



    fun fetchData(){
        state.value = CountryState.IsLoading(true)
        api.getData().enqueue(object :Callback<List<ResponseCountry>>{
            override fun onFailure(call: Call<List<ResponseCountry>>, t: Throwable) {
                state.value = CountryState.Error(t.message)
            }

            override fun onResponse(call: Call<List<ResponseCountry>>, response: Response<List<ResponseCountry>>) {
                if(response.isSuccessful){
                    val body = response.body() as List<ResponseCountry>
                    country.postValue(body)
                }else{
                    state.value = CountryState.Error("Terjadi Kesalahan.Gagal mendapatkan response")
                }
                state.value = CountryState.IsLoading(false)
            }

        })
    }

    fun getCountry() = country
    fun getState() = state

}

sealed class CountryState{
    data class ShowToast(var message : String) : CountryState()
    data class IsLoading(var state: Boolean = false) : CountryState()
    data class Error(var err : String?) : CountryState()
    data class IsSuccess(var what : Int? = null) : CountryState()
}