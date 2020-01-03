package ralph.stoffers.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
    }

    private fun initViews() {

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
