package com.example.planetfragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_questions.view.*

class QuestionsFragment : Fragment(), View.OnClickListener {

    private lateinit var answersListener: AnswersListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_questions, container, false)

        layout.most_moons.setOnClickListener(this)
        layout.largest_planet.setOnClickListener(this)
        layout.side_spinning.setOnClickListener(this)

        return layout

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        answersListener = context as AnswersListener
    }

    override fun onClick(view: View?) {

        view?.let { question ->
            answersListener.onSelected(question.id)
        }
    }
}
