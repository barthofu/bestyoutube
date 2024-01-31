package fr.bgili.bestyoutube.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.databinding.ActivityAddVideoBinding
import fr.bgili.bestyoutube.entities.Video
import fr.bgili.bestyoutube.validators.EmptyValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVideoBinding.inflate(layoutInflater)

        val categoryDao = (this.application as Application).database.categoryDao()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val categories = (application as Application).database
                    .categoryDao()
                    .findAll()

                runOnUiThread {
                    binding.selectCategory.adapter = android.widget.ArrayAdapter(
                        this@AddVideoActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories
                    )
                }
            }
        }

        setContentView(binding.root)
    }

    fun addVideo(view: View) {



        // get data
        val name = binding.editName.text.toString()
        val description = binding.editDescription.text.toString()
        val url = binding.editUrl.text.toString()
//        val category = binding.selectCategory.selectedItem as String

        // validate data
        val usernameEmptyValidation = EmptyValidator(name).validate()
        binding.editName.error =
            if (!usernameEmptyValidation.isSuccess)
                getString(usernameEmptyValidation.message) else null

        val descriptionEmptyValidation = EmptyValidator(description).validate()
        binding.editDescription.error =
            if (!descriptionEmptyValidation.isSuccess)
                getString(descriptionEmptyValidation.message) else null

        val urlEmptyValidation = EmptyValidator(url).validate()
        binding.editUrl.error =
            if (!urlEmptyValidation.isSuccess)
                getString(urlEmptyValidation.message) else null

        // create video entity
//        val video = Video(
//            name = name,
//            description = description,
//            url = url,
//            categoryId = 1
//        )



    }
}