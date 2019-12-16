package ralph.stoffers.weatherapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_cities.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.CitiesActivityViewModel
import ralph.stoffers.weatherapp.model.City

class CitiesActivity : AppCompatActivity() {
    private lateinit var viewModel: CitiesActivityViewModel
    private val citiesList = arrayListOf<City>()
    private val citiesAdapter = CityAdapter(citiesList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        supportActionBar?.title = getString(R.string.manage_cities)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvCities.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvCities.adapter = citiesAdapter
        createItemTouchHelper().attachToRecyclerView(rvCities)

        btAdd.setOnClickListener {
            viewModel.insertCity(etCity.text.toString().trim())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CitiesActivityViewModel::class.java)

        viewModel.success.observe(this, Observer {
            if (it != 0) {
                Toast.makeText(this, getString(it!!), Toast.LENGTH_LONG).show()
                etCity.setText("")
            }
        })

        viewModel.error.observe(this, Observer {
            if (it != 0) Toast.makeText(this, getString(it!!), Toast.LENGTH_LONG).show()
        })

        viewModel.cities.observe(this, Observer { cities ->
            citiesList.clear()
            citiesList.addAll(cities)
            citiesAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            // Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedCity = citiesList[viewHolder.adapterPosition]
                viewModel.deleteCity(deletedCity)
                val snackbar = Snackbar.make(
                    findViewById(R.id.rvCities),
                    getString(R.string.delete_success),
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(getString(R.string.undo)) { viewModel.insertCity(deletedCity) }
                snackbar.show()
            }
        }
        return ItemTouchHelper(callback)
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
