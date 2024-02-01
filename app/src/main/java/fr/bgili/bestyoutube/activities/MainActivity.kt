package fr.bgili.bestyoutube.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.adapters.VideoAdapter
import fr.bgili.bestyoutube.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoAdapter = VideoAdapter()
        binding.listVideos.layoutManager = LinearLayoutManager(this)
        binding.listVideos.adapter = videoAdapter


    }

    fun navigateToAddVideo(view: View) {
        startActivity(Intent(this, SaveVideoActivity::class.java))
    }
}