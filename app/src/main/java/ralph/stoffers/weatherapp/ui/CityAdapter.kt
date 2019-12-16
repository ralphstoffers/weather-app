package ralph.stoffers.weatherapp.ui

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item.view.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.City

internal class CityAdapter (private val cities: List<City>, private val context: Context) :
        RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    companion object {
        val CITY = "CITY"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        )
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnLongClickListener{
                val intent = Intent(context, EditCityActivity::class.java).apply {
                    putExtra(CITY, cities[adapterPosition])
                }
                startActivity(context, intent, null)
                true
            }
        }
        fun bind(city: City) {
            itemView.tvCityName.text = city.city
        }
    }
}