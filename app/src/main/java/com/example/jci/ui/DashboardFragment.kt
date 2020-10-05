package com.example.jci.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jci.R
import com.example.jci.data.ResultsItem
import com.example.jci.ui.adapter.RestaurantsAdapter
import com.example.jci.viewmodel.RestaurantsViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import androidx.preference.PreferenceManager
import com.example.jci.util.DistanceCalculator
import com.example.jci.util.KeyConstants
import com.example.jci.util.LiveSharedPreferences



class DashboardFragment: Fragment(){
    var viewmodel: RestaurantsViewModel? = null
    lateinit var adapter: RestaurantsAdapter
    var restaurantList: MutableList<ResultsItem>? = null


    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewmodel = ViewModelProviders.of(this).get(RestaurantsViewModel::class.java)

        viewmodel!!.getPlaces(KeyConstants.LATITUDE.toString(),KeyConstants.LONGITUDE.toString())
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_view.layoutManager = LinearLayoutManager(activity)

        initObservables()



    }


    private fun initObservables() {
        viewmodel?.placesResponse1?.observe(this, Observer {
            val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
            val liveSharedPreferences = LiveSharedPreferences(preferences)
            restaurantList = it as MutableList<ResultsItem>?
            val resList:List<ResultsItem> = it

            adapter = RestaurantsAdapter(it,KeyConstants.LATITUDE,KeyConstants.LONGITUDE,true)
            recycler_view.adapter = adapter

            liveSharedPreferences.getString("drop_select_preference","").observe(this, Observer {
                if(it != null) {
                    if (it.equals("1")) {
                        restaurantList?.sortWith(compareBy {
                            it.rating
                        })
                        restaurantList?.let { it1 -> adapter.update(it1) }
                        adapter.notifyDataSetChanged()


                    } else {

                        restaurantList?.sortWith(compareBy {
                            DistanceCalculator.distance(
                                KeyConstants.LATITUDE,
                                it.geometry.location.lat,
                                KeyConstants.LONGITUDE,
                                it.geometry.location.lng
                            )
                        })
                        restaurantList?.let { it1 -> adapter.update(it1) }
                        adapter.notifyDataSetChanged()

                    }
                }
            })

            liveSharedPreferences.getBoolean("distance_preference",false).observe(this, Observer { isKms ->
                if(isKms != null){
                    if(isKms){
                        adapter = RestaurantsAdapter(resList,KeyConstants.LATITUDE,KeyConstants.LONGITUDE,true)
                        recycler_view.adapter = adapter
                        adapter.notifyDataSetChanged()

                    }else{
                        adapter = RestaurantsAdapter(resList,KeyConstants.LATITUDE,KeyConstants.LONGITUDE,false)
                        recycler_view.adapter = adapter
                        adapter.notifyDataSetChanged()

                    }
                }

            })


        })

    }



}