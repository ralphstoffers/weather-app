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
import java.text.SimpleDateFormat
import java.util.*

internal class ForecastAdapter(
    private val forecastList: List<WeatherForecast>,
    private val context: Context
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        )
    }

    override fun getItemCount(): Int = forecastList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecast: WeatherForecast) {
            itemView.tvDateTime.text = formatDateTime(forecast.dateTime)
            itemView.tvDescription.text =
                context.getString(R.string.description, forecast.description)
            itemView.tvWind.text = context.getString(R.string.wind, forecast.wind)
            itemView.tvTemp.text =
                context.getString(R.string.temperature, forecast.temp.roundToInt())
            itemView.tvMaxTemp.text =
                context.getString(R.string.max_temperature, forecast.maxTempt.roundToInt())
            itemView.tvMinTemp.text =
                context.getString(R.string.min_temperature, forecast.minTemp.roundToInt())
            itemView.tvHumidity.text = context.getString(R.string.humidity, forecast.humidity)
            Glide.with(context)
                .load(String.format("http://openweathermap.org/img/wn/%s@2x.png", forecast.icon))
                .into(itemView.ivIcon)
        }
    }

    private fun formatDateTime(date: Date): String {
        val day = decideDay(date)
        return if(day != null) day + SimpleDateFormat("HH:mm").format(date)
        else SimpleDateFormat("EEEE HH:mm").format(date)
    }

    private fun decideDay(date: Date): String? {
        val cal = Calendar.getInstance()
        cal.time = date
        val day = cal.get(Calendar.DAY_OF_YEAR)
        cal.time = Date(System.currentTimeMillis())
        val currDay = cal.get(Calendar.DAY_OF_YEAR)
        return when {
            day == currDay -> "Today "
            day - currDay == 1 -> "Tomorrow "
            else -> null
        }
    }
}