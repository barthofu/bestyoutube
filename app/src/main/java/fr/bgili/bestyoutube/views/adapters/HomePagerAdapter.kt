package fr.bgili.bestyoutube.views.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.bgili.bestyoutube.views.fragments.ListVideosFragment

private const val NUM_TABS = 2

class HomePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val allVideosFragment = ListVideosFragment()
    private val favoriteVideosFragment = ListVideosFragment().apply {
        arguments = Bundle().apply {
            putBoolean("isFavoriteFiltered", true)
        }
    }

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> allVideosFragment
            1 -> favoriteVideosFragment
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}