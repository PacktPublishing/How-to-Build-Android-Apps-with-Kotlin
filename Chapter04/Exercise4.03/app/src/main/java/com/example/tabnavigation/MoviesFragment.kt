package com.example.tabnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val movieType: TextView = root.findViewById(R.id.movie_type)
        movieType.text = arguments?.getString(MOVIE_TYPE) ?: "Undefined Type"

        return root
    }

    companion object {

        private const val MOVIE_TYPE = "MOVIE_TYPE"

        @JvmStatic
        fun newInstance(movieType: String): MoviesFragment {
            return MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(MOVIE_TYPE, movieType)
                }
            }
        }
    }
}
