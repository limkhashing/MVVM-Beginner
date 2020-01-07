package com.kslimweb.mvvmtutorial.repository

import androidx.lifecycle.MutableLiveData
import com.kslimweb.mvvmtutorial.model.Place

class PlaceRepository {

    // act as a dummy singleton
    private val dataSet: ArrayList<Place> = ArrayList()

    companion object {
        private var instance: PlaceRepository? = null
        fun getInstance(): PlaceRepository? {
            if (instance == null) {
                instance = PlaceRepository()
            }
            return instance
        }
    }

    // Pretend to get data from a webservice or online source
    fun getNicePlaces(): MutableLiveData<List<Place>> {
        setPlaces()
        val data: MutableLiveData<List<Place>> = MutableLiveData()
        data.value = dataSet
        return data
    }

    private fun setPlaces() {
        dataSet.add(
            Place(
                "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                "Havasu Falls"
            )
        )
        dataSet.add(
            Place(
                "https://i.redd.it/tpsnoz5bzo501.jpg",
                "Trondheim"
            )
        )
        dataSet.add(
            Place(
                "https://i.redd.it/qn7f9oqu7o501.jpg",
                "Portugal"
            )
        )
        dataSet.add(
            Place(
                "https://i.redd.it/j6myfqglup501.jpg",
                "Rocky Mountain National Park"
            )
        )
        dataSet.add(
            Place(
                "https://i.redd.it/0h2gm1ix6p501.jpg",
                "Havasu Falls"
            )
        )
        dataSet.add(
            Place(
                "https://i.redd.it/k98uzl68eh501.jpg",
                "Mahahual"
            )
        )
        dataSet.add(
            Place(
                "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                "Frozen Lake"
            )
        )
        dataSet.add(
            Place(
                "https://i.redd.it/obx4zydshg601.jpg",
                "Austrailia"
            )
        )
    }
}