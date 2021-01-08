package com.example.dualpanelayouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context

class ListFragment : Fragment(), View.OnClickListener {

    private lateinit var starSignListener: StarSignListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StarSignListener) {
            starSignListener = context
        } else {
            throw RuntimeException("Must implement StarSignListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val starSigns = listOf<View>(
            view.findViewById(R.id.aquarius),
            view.findViewById(R.id.pisces),
            view.findViewById(R.id.aries),
            view.findViewById(R.id.taurus),
            view.findViewById(R.id.gemini),
            view.findViewById(R.id.cancer),
            view.findViewById(R.id.leo),
            view.findViewById(R.id.virgo),
            view.findViewById(R.id.libra),
            view.findViewById(R.id.scorpio),
            view.findViewById(R.id.sagittarius),
            view.findViewById(R.id.capricorn)
        )

        starSigns.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {

        v?.let { starSign ->
            starSignListener.onSelected(starSign.id)
        }
    }
}
