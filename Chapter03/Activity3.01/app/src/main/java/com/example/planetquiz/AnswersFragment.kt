package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnswersFragment : Fragment(), View.OnClickListener {

    var questionId: Int = NO_QUESTION_SET

    private val headerText: TextView?
        get() = view?.findViewById(R.id.header_text)

    private val answer: TextView?
        get() = view?.findViewById(R.id.answer)
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_answers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planets = listOf<View>(
            view.findViewById(R.id.mercury),
            view.findViewById(R.id.venus),
            view.findViewById(R.id.earth),
            view.findViewById(R.id.mars),
            view.findViewById(R.id.jupiter),
            view.findViewById(R.id.saturn),
            view.findViewById(R.id.uranus),
            view.findViewById(R.id.neptune)
        )

        planets.forEach {
            it.setOnClickListener(this)
        }

        questionId = arguments?.getInt(QUESTION_ID) ?: NO_QUESTION_SET

        //Set Header Text
        when (questionId) {
            R.id.largest_planet -> {
                headerText?.text = getString(R.string.largest_planet)
            }
            R.id.most_moons -> {
                headerText?.text = getString(R.string.most_moons)
            }
            R.id.side_spinning -> {
                headerText?.text = getString(R.string.side_spinning)
            }
        }
    }

    override fun onClick(v: View?) {

        when (questionId) {
            R.id.largest_planet -> {
                if (v?.id == R.id.jupiter) {
                    answer?.text = getString(R.string.jupiter_answer, getString(R.string.correct))
                } else {
                    answer?.text = getString(R.string.jupiter_answer, getString(R.string.wrong))
                }
            }
            R.id.most_moons -> {
                if (v?.id == R.id.saturn) {
                    answer?.text = getString(R.string.saturn_answer, getString(R.string.correct))
                } else {
                    answer?.text = getString(R.string.saturn_answer, getString(R.string.wrong))
                }
            }
            R.id.side_spinning -> {
                if (v?.id == R.id.uranus) {
                    answer?.text = getString(R.string.uranus_answer, getString(R.string.correct))
                } else {
                    answer?.text = getString(R.string.uranus_answer, getString(R.string.wrong))
                }
            }
        }
    }

    companion object {

        private const val QUESTION_ID = "QUESTION_ID"
        private const val NO_QUESTION_SET = 0

        @JvmStatic
        fun newInstance(questionId: Int) =
            AnswersFragment().apply {
                arguments = Bundle().apply {
                    putInt(QUESTION_ID, questionId)
                }
            }
    }


}
