package fr.bgili.bestyoutube.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import fr.bgili.bestyoutube.Application
import fr.bgili.bestyoutube.adapters.VideoAdapter
import fr.bgili.bestyoutube.databinding.FragmentListVideosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListVideosFragment : Fragment() {

    private lateinit var binding: FragmentListVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListVideosBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setVideoList()

        return binding.root
    }

    private fun setVideoList() {

        val videoAdapter = VideoAdapter()
        binding.listVideos.layoutManager = LinearLayoutManager(this.context)
        binding.listVideos.adapter = videoAdapter

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val videos = (activity?.application as Application).database
                    .videoDao()
                    .findAll()

                activity?.runOnUiThread {
                    videoAdapter.submitList(videos)
                }
            }
        }
    }
}