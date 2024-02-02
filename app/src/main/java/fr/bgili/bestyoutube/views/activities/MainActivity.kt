package fr.bgili.bestyoutube.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.R
import fr.bgili.bestyoutube.views.adapters.HomePagerAdapter
import fr.bgili.bestyoutube.views.adapters.VideoAdapter
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

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = HomePagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = getString(R.string.all_videos_title)
            } else {
                tab.text = getString(R.string.favorited_videos_title)
            }
        }.attach()

    }

    fun navigateToAddVideo(view: View) {
        startActivity(Intent(this, SaveVideoActivity::class.java))
    }
}