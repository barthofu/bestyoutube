package fr.bgili.bestyoutube.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.databinding.ActivityAddVideoBinding
import fr.bgili.bestyoutube.entities.Video
import fr.bgili.bestyoutube.validators.EmptyValidator
import fr.bgili.bestyoutube.validators.URLValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            category = category
        )

        // save video entity
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                (application as Application).database
                    .videoDao()
                    .insert(video)

                runOnUiThread {
                    startActivity(Intent(this@AddVideoActivity, MainActivity::class.java))
                }
//                finish()
            }
        }



    }
}