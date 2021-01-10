package com.example.fragmentintro

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StyleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StyleFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val boldButton = view.findViewById<Button>(R.id.bold_button)
        val italicButton = view.findViewById<Button>(R.id.italic_button)
        val resetButton = view.findViewById<Button>(R.id.reset_button)
        val helloWorldTextView = view.findViewById<TextView>(R.id.hello_world)

        boldButton.setOnClickListener {
            if (helloWorldTextView.typeface?.isItalic == true) helloWorldTextView.setTypeface(helloWorldTextView.typeface, Typeface.BOLD_ITALIC) else helloWorldTextView.setTypeface(null, Typeface.BOLD)
        }

        italicButton.setOnClickListener {
            if (helloWorldTextView.typeface?.isBold == true) helloWorldTextView.setTypeface(helloWorldTextView.typeface, Typeface.BOLD_ITALIC) else helloWorldTextView.setTypeface(null, Typeface.ITALIC)
        }

        resetButton.setOnClickListener {
            helloWorldTextView.setTypeface(null, Typeface.NORMAL)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StyleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
