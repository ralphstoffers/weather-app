package ralph.stoffers.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import ralph.stoffers.weatherapp.R

class CitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        supportActionBar?.title = getString(R.string.manage_cities)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
