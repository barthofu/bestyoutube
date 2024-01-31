package fr.bgili.bestyoutube.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.bgili.bestyoutube.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateToAddVideo(view: View) {
        startActivity(Intent(this, AddVideoActivity::class.java))
    }
}