package ralph.stoffers.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ralph.stoffers.weatherapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.overview)

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener {
            startActivity(Intent(this, CitiesActivity::class.java))
        }
    }
}
