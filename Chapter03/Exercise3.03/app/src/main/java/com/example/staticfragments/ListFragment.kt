package com.example.staticfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */

interface StarSignListener {
    fun onSelected(id: Int)
}

class ListFragment : Fragment(), View.OnClickListener {

    private lateinit var starSignListener: StarSignListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        starSignListener = context as StarSignListener
    }

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
            starSignListener.onSelected(starSign.id)
        }
    }
}
