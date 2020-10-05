package com.example.jci.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jci.data.ResultsItem
import com.example.jci.data.Root
import com.example.jci.network.BackEndApi
import com.example.jci.network.WebServiceClient
import com.example.jci.util.DistanceCalculator
import com.example.jci.util.KeyConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsViewModel(application: Application) : AndroidViewModel(application), Callback<Root> {
    var placesResponse1: MutableLiveData<List<ResultsItem>>? = null



    init {
        placesResponse1 = MutableLiveData()
    }


    fun getPlaces(lat:String,lon:String){
        WebServiceClient.client.create(BackEndApi::class.java).getPlaces(lat+","+lon,KeyConstants.PLACES_RADIUS,KeyConstants.PLACES_TYPE,KeyConstants.API_KEY).enqueue(this)

    }

    override fun onFailure(call: Call<Root>?, t: Throwable?) {
        placesResponse1?.value = null
    }

    override fun onResponse(call: Call<Root>?, response: Response<Root>?) {
        placesResponse1?.value = response?.body()?.results?.sortedBy {
            DistanceCalculator.distance(KeyConstants.LATITUDE,it.geometry.location.lat,KeyConstants.LONGITUDE,it.geometry.location.lng)
        }
    }

}