package ralph.stoffers.weatherapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_cities.*
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.model.CitiesActivityViewModel

class CitiesActivity : AppCompatActivity() {
    private lateinit var viewModel: CitiesActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        supportActionBar?.title = getString(R.string.manage_cities)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        btAdd.setOnClickListener {
            viewModel.insertCity(etCity.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CitiesActivityViewModel::class.java)

        viewModel.success.observe(this, Observer {
            if (it != 0) Toast.makeText(this, getString(it!!), Toast.LENGTH_LONG).show()
        })

        viewModel.error.observe(this, Observer {
            if (it != 0) Toast.makeText(this, getString(it!!), Toast.LENGTH_LONG).show()
        })
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
