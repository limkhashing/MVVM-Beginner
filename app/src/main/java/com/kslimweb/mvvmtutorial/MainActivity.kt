package com.kslimweb.mvvmtutorial

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kslimweb.mvvmtutorial.adapter.PlaceAdapter
import com.kslimweb.mvvmtutorial.model.Place
import com.kslimweb.mvvmtutorial.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var placeAdapter: PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.init()
        initRecyclerView()

        mainViewModel.getPlaces().observe(this, Observer {
            placeAdapter.notifyItemInserted(mainViewModel.getPlaces().value?.size?.minus(1) ?: 0)
        })

        mainViewModel.getIsUpdating()?.observe(this, Observer { isUpdating ->
            isUpdating?.let {
                if (it)
                    showProgressBar()
                else {
                    hideProgressBar()
                    rv_place.smoothScrollToPosition(mainViewModel.getPlaces().value?.size?.minus(1) ?: 0)
                }
            }
        })

        fab.setOnClickListener {
            mainViewModel.addNewValue(Place("https://i.imgur.com/ZcLLrkY.jpg", "Washington"))
        }
    }

    private fun initRecyclerView() {
        placeAdapter = PlaceAdapter(mainViewModel.getPlaces().value, applicationContext)
        rv_place.adapter = placeAdapter
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}
