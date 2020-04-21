package com.example.dynamicfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(), View.OnClickListener {

    private lateinit var starSignListener: StarSignListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_list, container, false)

        layout.aquarius.setOnClickListener(this)
        layout.pisces.setOnClickListener(this)
        layout.aries.setOnClickListener(this)
        layout.taurus.setOnClickListener(this)
        layout.gemini.setOnClickListener(this)
        layout.cancer.setOnClickListener(this)
        layout.leo.setOnClickListener(this)
        layout.virgo.setOnClickListener(this)
        layout.libra.setOnClickListener(this)
        layout.scorpio.setOnClickListener(this)
        layout.sagittarius.setOnClickListener(this)
        layout.capricorn.setOnClickListener(this)

        return layout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        starSignListener = context as StarSignListener
    }

    override fun onClick(view: View?) {

        view?.let { starSign ->
            starSignListener.onSelected(starSign.id)
        }
    }

}
