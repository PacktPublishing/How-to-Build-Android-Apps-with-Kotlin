package com.example.fragmentintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_counter.*

/**
 * A simple [Fragment] subclass.
 */
class CounterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add.setOnClickListener {
            var counterValue = counter.text.toString().toInt()
            counter.text = (++counterValue).toString()
        }

        minus.setOnClickListener {
            var counterValue = counter.text.toString().toInt()
            if (counterValue > 0) counter.text = (--counterValue).toString()
        }
    }
}
