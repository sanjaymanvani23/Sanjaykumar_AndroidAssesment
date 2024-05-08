package com.otherTallguy.dagger2example

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.otherTallguy.dagger2example.model.Country
import com.otherTallguy.dagger2example.model.Universities
import com.otherTallguy.dagger2example.viewmodel.MainViewModel
import com.otherTallguy.dagger2example.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var countryList: ArrayList<Country>
    private lateinit var univerSitiesList: ArrayList<Universities>
    private lateinit var countriesAdapter: CountryListAdapter


    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory // Dagger will provide the object to this variable through field injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CountriesApplication).applicationComponent.inject(this)
        // what the above code do is, It will check the file for any of the @Inject property and if there are any
        // it will inject the correct object to them. Here it is mainViewModelFactory




        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        countryList = arrayListOf()
        univerSitiesList = arrayListOf()
        mainViewModel.refresh("United Arab Emirates")

        countriesAdapter = CountryListAdapter(arrayListOf(), OnClickListener { item ->
            Toast.makeText(this,"Clicked Country is " + item.country, Toast.LENGTH_LONG).show()
            container.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().replace(R.id.container,DetailFragment(item,OnRefreshClick {
                refreshList()
            })).commit()
        })

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mainViewModel.refresh("United Arab Emirates")
        }
        observeViewModel()
    }
    private fun observeViewModel() {

       /* mainViewModel.countriesLiveData.observe(this) { countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countryList.addAll(it)
                countriesAdapter.updateCountries(it)
            }
        }*/

        mainViewModel.universitiesLiveData.observe(this) { countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                univerSitiesList.addAll(it)
                countriesAdapter.updateCountries(it)
            }
        }

        mainViewModel.countryLoadError.observe(this) { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        }

        mainViewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        }
    }

    fun refreshList(){
        mainViewModel.refresh("United Arab Emirates")
    }

    override fun onBackPressed() {

        if(container.isVisible){
            container.visibility = View.GONE
        }else{
            super.onBackPressed()
        }
    }

    class OnRefreshClick(val clickListener: () -> Unit) {
        fun onRefresh() = clickListener()
    }
}