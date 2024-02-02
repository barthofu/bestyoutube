package fr.bgili.bestyoutube.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.R
import fr.bgili.bestyoutube.databinding.ActivityDetailVideoBinding
import fr.bgili.bestyoutube.data.entities.Video
import fr.bgili.bestyoutube.views.fragments.ListVideosFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailVideoBinding

    private val videoId: Long by lazy {
        intent.getLongExtra("videoId", -1)
    }
    private var favorited: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {

                val video = (application as Application).database
                    .videoDao()
                    .findById(videoId)

                runOnUiThread {
                    binding.textDetailedName.text = video.name
                    binding.textDetailedDescription.text = video.description
                    binding.textDetailedURL.text = video.url
                    binding.textDetailedCategory.text = video.category

                    favorited = video.favorite
                    renderFavoriteButton()
                }
            }
        }
    }

    private fun renderFavoriteButton() {
        if (favorited) {
            val uri = "@android:drawable/star_big_on"
            val imageResource = resources.getIdentifier(uri, null, packageName)
            binding.buttonFavorite.setImageResource(imageResource)
        } else {
            binding.buttonFavorite.setImageResource(R.drawable.baseline_star_border_24)
        }
    }

    fun editVideo(view: View) {
        val intent = Intent(view.context, SaveVideoActivity::class.java)
        intent.putExtra("videoId", videoId)
        startActivity(intent)
    }

    fun toggleFavorite(view: View) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {

                favorited = !favorited

                (application as Application).database
                    .videoDao()
                    .updateFavorite(videoId, favorited)

                runOnUiThread {
                    renderFavoriteButton()
                }
            }
        }
    }

    fun deleteVideo(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.delete_confirmation)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {

                        (application as Application).database
                            .videoDao()
                            .deleteById(videoId)

                        runOnUiThread {
                            startActivity(Intent(this@DetailVideoActivity, MainActivity::class.java))
                        }
                    }
                }
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }

        builder.create().show()
    }


}