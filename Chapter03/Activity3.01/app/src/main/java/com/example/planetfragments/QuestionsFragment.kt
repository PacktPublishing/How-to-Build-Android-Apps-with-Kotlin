package com.example.planetfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_questions.*

class QuestionsFragment : Fragment(), View.OnClickListener {

    private lateinit var answersListener: AnswersListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        answersListener = context as AnswersListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planets = listOf(most_moons, largest_planet, side_spinning)

        planets.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {

        view?.let { question ->
            answersListener.onSelected(question.id)
        }
    }
}
