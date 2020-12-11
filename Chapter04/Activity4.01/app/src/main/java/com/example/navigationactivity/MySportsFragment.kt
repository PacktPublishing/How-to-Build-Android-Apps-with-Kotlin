package com.example.navigationactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_mysports.view.*

class MySportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_mysports, container, false)

        view.basketball?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nav_mysports_to_basketball, null)
        )

        view.football?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nav_mysports_to_football, null)
        )

        view.hockey?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nav_mysports_to_hockey, null)
        )

        return view
    }
}
