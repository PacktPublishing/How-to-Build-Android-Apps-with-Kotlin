package com.example.jetpackfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), View.OnClickListener {

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

        starSigns.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {

        view?.let { starSign ->

            val action =
                StarSignListFragmentDirections.actionStarSignListToStarSignDetail(starSign.id)
            findNavController().navigate(action)
        }
    }

}
