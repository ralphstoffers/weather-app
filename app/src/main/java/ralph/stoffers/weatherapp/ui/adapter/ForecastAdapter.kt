package ralph.stoffers.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.forecast_item.view.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.entity.WeatherForecast
import kotlin.math.roundToInt

internal class ForecastAdapter (private val forecastList: List<WeatherForecast>, private val context: Context)
    : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        )
    }

    override fun getItemCount(): Int = forecastList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecast: WeatherForecast) {
            itemView.tvCityName.text = forecast.name
            itemView.tvDescription.text = context.getString(R.string.description, forecast.description)
            itemView.tvWind.text = context.getString(R.string.wind, forecast.wind)
            itemView.tvTemp.text = context.getString(R.string.temperature, forecast.temp.roundToInt())
            Glide.with(context).load(String.format("http://openweathermap.org/img/wn/%s@2x.png", forecast.icon)).into(itemView.ivIcon)
        }
    }
}