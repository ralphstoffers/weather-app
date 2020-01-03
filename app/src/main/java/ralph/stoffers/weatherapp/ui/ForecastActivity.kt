package ralph.stoffers.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_forecast.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.entity.WeatherForecast
import ralph.stoffers.weatherapp.model.viewmodel.ForecastActivityViewModel
import ralph.stoffers.weatherapp.ui.adapter.CurrentWeatherAdapter
import ralph.stoffers.weatherapp.ui.adapter.ForecastAdapter

class ForecastActivity : AppCompatActivity() {
    private lateinit var viewModel: ForecastActivityViewModel
    private lateinit var cityName: String
    private val forecastList = arrayListOf<WeatherForecast>()
    private val forecastAdapter = ForecastAdapter(forecastList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        cityName = intent.getStringExtra(CurrentWeatherAdapter.CITY_NAME)!!

        supportActionBar?.title = cityName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ForecastActivityViewModel::class.java)
        viewModel.forecastList.observe(this, Observer{
            forecastList.clear()
            forecastList.addAll(it)
            forecastAdapter.notifyDataSetChanged()
        })
        viewModel.getWeatherForecast(cityName)
    }

    private fun initViews() {
        rvForecasts.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvForecasts.adapter = forecastAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
