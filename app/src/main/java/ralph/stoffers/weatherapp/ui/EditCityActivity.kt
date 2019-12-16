package ralph.stoffers.weatherapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ralph.stoffers.weatherapp.R

import kotlinx.android.synthetic.main.activity_edit_city.*
import kotlinx.android.synthetic.main.content_edit_city.*
import ralph.stoffers.weatherapp.model.City
import ralph.stoffers.weatherapp.model.EdityCityActivityViewModel

class EditCityActivity : AppCompatActivity() {
    private lateinit var viewModel: EdityCityActivityViewModel
    lateinit var cityToEdit: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_city)
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.change_city)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EdityCityActivityViewModel::class.java)

        viewModel.success.observe(this, Observer {
            if (it) {
                Toast.makeText(this, getString(R.string.update_success), Toast.LENGTH_LONG).show()
                finish()
            }

        })

        viewModel.error.observe(this, Observer {
            if (it != 0) Toast.makeText(this, getString(it!!), Toast.LENGTH_LONG).show()
        })
    }

    private fun initViews() {
        cityToEdit = intent.getParcelableExtra(CityAdapter.CITY)
        etChangeCity.setText(cityToEdit.city)

        btSave.setOnClickListener {
            cityToEdit.city = etChangeCity.text.toString().trim()
            viewModel.updateCity(cityToEdit)
        }
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
