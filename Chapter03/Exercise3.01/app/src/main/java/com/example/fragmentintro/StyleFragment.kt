package com.example.fragmentintro

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_style.*

/**
 * A simple [Fragment] subclass.
 */
class StyleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bold_button.setOnClickListener {
            if (hello_world.typeface.isItalic) hello_world.setTypeface(hello_world.typeface, Typeface.BOLD_ITALIC) else hello_world.setTypeface(null, Typeface.BOLD)
        }

        italic_button.setOnClickListener {
            if (hello_world.typeface.isBold) hello_world.setTypeface(hello_world.typeface, Typeface.BOLD_ITALIC) else hello_world.setTypeface(null, Typeface.ITALIC)
        }

        reset_button.setOnClickListener {
            hello_world.setTypeface(Typeface.create(hello_world.typeface, Typeface.NORMAL), Typeface.NORMAL)
        }
    }
}
