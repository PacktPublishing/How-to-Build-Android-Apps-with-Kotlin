package com.example.navigationactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class MySportsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_mysports, container, false)

        view.findViewById<Button>(R.id.basketball)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nav_mysports_to_basketball, null)
        )

        view.findViewById<Button>(R.id.football)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nav_mysports_to_football, null)
        )

        view.findViewById<Button>(R.id.hockey)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nav_mysports_to_hockey, null)
        )

        return view
    }
}
