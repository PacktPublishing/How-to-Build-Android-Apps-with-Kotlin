package com.example.planetfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_answers.*
import kotlinx.android.synthetic.main.fragment_answers.view.*


const val NO_QUESTION_SET = 0

class AnswersFragment : Fragment(), View.OnClickListener {

    var questionId: Int = NO_QUESTION_SET

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_answers, container, false)

        layout.mercury.setOnClickListener(this)
        layout.venus.setOnClickListener(this)
        layout.earth.setOnClickListener(this)
        layout.mars.setOnClickListener(this)
        layout.jupiter.setOnClickListener(this)
        layout.saturn.setOnClickListener(this)
        layout.uranus.setOnClickListener(this)
        layout.neptune.setOnClickListener(this)

        questionId = arguments?.getInt(QUESTION_ID) ?: NO_QUESTION_SET

        //Set Header Text
        when(questionId) {
            R.id.largest_planet ->  {
                layout.header_text.text = getString(R.string.largest_planet)
            }
            R.id.most_moons ->  {
                layout.header_text.text = getString(R.string.most_moons)
            }
            R.id.side_spinning ->  {
                layout.header_text.text = getString(R.string.side_spinning)
            }
        }

        return layout
    }

    companion object {

        @JvmStatic
        fun newInstance(questionId: Int) =
            AnswersFragment().apply {

                arguments = Bundle().apply {
                    putInt(QUESTION_ID, questionId)
                }

            }
    }

    override fun onClick(view: View?) {

          when(questionId) {
              R.id.largest_planet ->  {
                  if (view?.id == R.id.jupiter) {
                        answer.text = getString(R.string.jupiter_answer, getString(R.string.correct))
                  }
                  else {
                      answer.text = getString(R.string.jupiter_answer, getString(R.string.wrong))
                  }
              }
              R.id.most_moons ->  {
                  if (view?.id == R.id.saturn) {
                      answer.text = getString(R.string.saturn_answer, getString(R.string.correct))
                  }
                  else {
                      answer.text = getString(R.string.saturn_answer, getString(R.string.wrong))
                  }
              }
              R.id.side_spinning ->  {
                  if (view?.id == R.id.uranus) {
                      answer.text = getString(R.string.uranus_answer, getString(R.string.correct))
                  }
                  else {
                      answer.text = getString(R.string.uranus_answer, getString(R.string.wrong))
                  }
              }
          }

    }


}