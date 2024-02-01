package fr.bgili.bestyoutube.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.bgili.bestyoutube.fragments.ListVideosFragment

private const val NUM_TABS = 2

class HomePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {

        val listVideosFragment = ListVideosFragment()

        if (position == 1) {
            listVideosFragment.arguments = Bundle().apply {
                putBoolean("isFavorite", true)
            }
        }

        return listVideosFragment
    }
}