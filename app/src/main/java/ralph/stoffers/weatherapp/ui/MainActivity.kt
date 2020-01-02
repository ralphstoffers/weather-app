package ralph.stoffers.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.entity.CurrentWeather
import ralph.stoffers.weatherapp.model.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private val weatherList = arrayListOf<CurrentWeather>()

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

        viewModel.weatherData.observe(this, Observer { weather ->
            weatherList.clear()
            weatherList.addAll(weather)
        })

        viewModel.getCurrentWeather()

    }

    private fun initViews() {
        fab.setOnClickListener {
            startActivity(Intent(this, CitiesActivity::class.java))
        }
    }
}
