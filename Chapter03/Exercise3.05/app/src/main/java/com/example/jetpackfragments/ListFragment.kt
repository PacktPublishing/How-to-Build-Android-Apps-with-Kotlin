package com.example.jetpackfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val starSigns = listOf(
            aquarius,
            pisces,
            aries,
            taurus,
            gemini,
            cancer,
            leo,
            virgo,
            libra,
            scorpio,
            sagittarius,
            capricorn
        )

        starSigns.forEach { starSign ->
            val fragmentBundle = Bundle()
            fragmentBundle.putInt(STAR_SIGN_ID, starSign.id)
            starSign.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.star_sign_id_action,
                    fragmentBundle
                )
            )
        }
    }
}
