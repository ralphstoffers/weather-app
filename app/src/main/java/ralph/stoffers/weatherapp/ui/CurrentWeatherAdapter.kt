package ralph.stoffers.weatherapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_item.view.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.entity.CurrentWeather

internal class CurrentWeatherAdapter (private val weather: List<CurrentWeather>, private val context: Context)
    : RecyclerView.Adapter<CurrentWeatherAdapter.ViewHolder>() {

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
                val intent = Intent(context, EditCityActivity::class.java).apply {
                }
                ContextCompat.startActivity(context, intent, null)
                true
            }
        }
        fun bind(weather: CurrentWeather) {
            itemView.tvCityName.text = weather.name
            itemView.tvDescription.text = weather.description
            itemView.tvWind.text = weather.wind
            itemView.tvTemp.text = weather.temp.toString()
        }
    }

}