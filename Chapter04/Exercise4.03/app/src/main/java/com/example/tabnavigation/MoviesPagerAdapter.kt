package com.example.tabnavigation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES = arrayOf(
        R.string.action,
        R.string.comedy,
        R.string.drama,
        R.string.sci_fi,
        R.string.family,
        R.string.crime,
        R.string.history
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return MoviesFragment.newInstance(context.resources.getString(TAB_TITLES[position]))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show total pages.
        return TAB_TITLES.size
    }
}
