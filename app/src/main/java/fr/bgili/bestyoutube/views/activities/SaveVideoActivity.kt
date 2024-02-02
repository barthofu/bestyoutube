package fr.bgili.bestyoutube.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.R
import fr.bgili.bestyoutube.databinding.ActivitySaveVideoBinding
import fr.bgili.bestyoutube.data.entities.Video
import fr.bgili.bestyoutube.utils.validators.EmptyValidator
import fr.bgili.bestyoutube.utils.validators.URLValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveVideoBinding
    private val videoId: Long by lazy {
        intent.getLongExtra("videoId", -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (videoId != -1L) {

            setTitle(R.string.title_activity_edit_video)
            binding.buttonAdd.text = getString(R.string.save)

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {

                    val video = (application as Application).database
                        .videoDao()
                        .findById(videoId)

                    runOnUiThread {
                        binding.editName.setText(video.name)
                        binding.editDescription.setText(video.description)
                        binding.editUrl.setText(video.url)

                        // set the spinner to the video's category
                        val selectAdapter = ArrayAdapter.createFromResource(
                            this@SaveVideoActivity,
                            R.array.categories,
                            android.R.layout.simple_spinner_item
                        )
                        selectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.selectCategory.adapter = selectAdapter
                        binding.selectCategory.setSelection(selectAdapter.getPosition(video.category))
                    }
                }
            }
        }
    }

    fun addVideo(view: View) {

        // get data
        val name = binding.editName.text.toString()
        val description = binding.editDescription.text.toString()
        val url = binding.editUrl.text.toString()
        val category = binding.selectCategory.selectedItem as String

        var atLeastOneError = false

        // validate data
        val usernameEmptyValidation = EmptyValidator(name).validate()
        if (!usernameEmptyValidation.isSuccess) {
            binding.editName.error =
                getString(usernameEmptyValidation.message)
            atLeastOneError = true
        }

        val descriptionEmptyValidation = EmptyValidator(description).validate()
        if (!descriptionEmptyValidation.isSuccess) {
            binding.editDescription.error =
                getString(descriptionEmptyValidation.message)
            atLeastOneError = true
        }

        val urlEmptyValidation = EmptyValidator(url).validate()
        if (!urlEmptyValidation.isSuccess) {
            binding.editUrl.error =
                getString(urlEmptyValidation.message)
            atLeastOneError = true
        }

        val invalidURLValidator = URLValidator(url).validate()
        if (!invalidURLValidator.isSuccess) {
            binding.editUrl.error =
                getString(invalidURLValidator.message)
            atLeastOneError = true
        }

        if (atLeastOneError) return

        // create video entity
        val video = Video(
            name = name,
            description = description,
            url = url,
            category = category,
            favorite = false
        )

        if (videoId != -1L) {
            video.id = videoId
        }

        // save video entity
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {

                val videoDao = (application as Application).database
                    .videoDao()

                if (videoId != -1L) {
                    videoDao.update(video)
                } else {
                    videoDao.insert(video)
                }

                runOnUiThread {
                    startActivity(Intent(this@SaveVideoActivity, MainActivity::class.java))
                }
            }
        }



    }
}