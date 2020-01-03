package ralph.stoffers.weatherapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.weather_item.view.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.entity.CurrentWeather
import ralph.stoffers.weatherapp.ui.ForecastActivity
import kotlin.math.roundToInt

internal class CurrentWeatherAdapter (private val weather: List<CurrentWeather>, private val context: Context)
    : RecyclerView.Adapter<CurrentWeatherAdapter.ViewHolder>() {

    companion object {
        val CITY_NAME = "CITYNAME"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        )
    }

    override fun getItemCount(): Int = weather.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weather[position])
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnLongClickListener{
                val intent = Intent(context, ForecastActivity::class.java).apply {
                    putExtra(CITY_NAME, weather[adapterPosition].name)
                }
                ContextCompat.startActivity(context, intent, null)
                true
            }
        }
        fun bind(weather: CurrentWeather) {
            itemView.tvCityName.text = weather.name
            itemView.tvDescription.text = context.getString(R.string.description, weather.description)
            itemView.tvWind.text = context.getString(R.string.wind, weather.wind)
            itemView.tvTemp.text = context.getString(R.string.temperature, weather.temp.roundToInt())
            Glide.with(context).load(String.format("http://openweathermap.org/img/wn/%s@2x.png", weather.icon)).into(itemView.ivIcon)
        }
    }

}