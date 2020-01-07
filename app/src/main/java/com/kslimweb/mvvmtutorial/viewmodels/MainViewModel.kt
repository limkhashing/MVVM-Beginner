package com.kslimweb.mvvmtutorial.viewmodels

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kslimweb.mvvmtutorial.model.Place
import com.kslimweb.mvvmtutorial.repository.PlaceRepository


class MainViewModel : ViewModel() {
    private var places = MutableLiveData<List<Place>>()
    private var placeRepo: PlaceRepository? = null
    private val isUpdating = MutableLiveData<Boolean>()

    fun init() {
        placeRepo = PlaceRepository.getInstance()
        placeRepo?.let {
            places = it.getNicePlaces()
        }
    }

    fun getPlaces(): LiveData<List<Place>> {
        return places
    }

    fun getIsUpdating(): LiveData<Boolean?>? {
        return isUpdating
    }

    fun addNewValue(place: Place) {
        isUpdating.value = true
        DummyAsynTask(place, this).execute()
    }

    companion object {
        class DummyAsynTask(private val place: Place, private val mainViewModel: MainViewModel) : AsyncTask<Any, Any, Any>() {

            override fun doInBackground(vararg params: Any?) {
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            override fun onPostExecute(result: Any?) {
                super.onPostExecute(result)
                val currentPlaces: MutableList<Place> = mainViewModel.places.value as MutableList<Place>
                currentPlaces.add(place)
                mainViewModel.places.postValue(currentPlaces)
                mainViewModel.isUpdating.postValue(false)
            }
        }
    }

}
