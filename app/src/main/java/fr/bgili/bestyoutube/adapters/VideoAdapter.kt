package fr.bgili.bestyoutube.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.bgili.bestyoutube.R
import fr.bgili.bestyoutube.activities.DetailVideoActivity
import fr.bgili.bestyoutube.entities.Video

class VideoAdapter : ListAdapter<Video, VideoAdapter.ViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video: Video = getItem(position)
        holder.bind(video)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var textName: TextView = view.findViewById(R.id.textName)
        private var textDescription: TextView = view.findViewById(R.id.textDescription)

        init {
            view.setOnClickListener {
                val intent = Intent(view.context, DetailVideoActivity::class.java)
                intent.putExtra("videoId", getItem(adapterPosition).id)
                view.context.startActivity(intent)
            }
        }

        fun bind(video: Video) {
            textName.text = video.name
            textDescription.text = video.description
        }
    }
}

class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}

