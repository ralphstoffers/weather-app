package ralph.stoffers.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.entity.City
import ralph.stoffers.weatherapp.model.entity.CurrentWeather
import ralph.stoffers.weatherapp.model.viewmodel.MainActivityViewModel
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private val cityList = arrayListOf<City>()
    private val weatherList = mutableListOf<CurrentWeather>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.overview)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.cities.observe(this, Observer{ cities  ->
            cityList.clear()
            cityList.addAll(cities)
            viewModel.getCurrentWeather(cities)
        })
        viewModel.weatherList.observe(this, Observer {
            weatherList.clear()
            weatherList.addAll(it)
        })
    }

    private fun initViews() {
        fab.setOnClickListener {
            startActivity(Intent(this, CitiesActivity::class.java))
        }
    }
}
