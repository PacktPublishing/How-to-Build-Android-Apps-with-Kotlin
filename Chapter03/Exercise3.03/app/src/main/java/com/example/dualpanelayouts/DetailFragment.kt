package com.example.dualpanelayouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    fun setStarSignData(starSignId: Int) {

        //Some text below should in production be string resources, done as hardcoded text here for simplicity
        when (starSignId) {
            R.id.aquarius -> {
                star_sign.text = getString(R.string.aquarius)
                symbol.text = getString(R.string.symbol, "Water Carrier")
                date_range.text = getString(R.string.date_range, "January 20 - February 18")
            }
            R.id.pisces -> {
                star_sign.text = getString(R.string.pisces)
                symbol.text = getString(R.string.symbol, "Fish")
                date_range.text = getString(R.string.date_range, "February 19 - March 20")
            }
            R.id.aries -> {
                star_sign.text = getString(R.string.aries)
                symbol.text = getString(R.string.symbol, "Ram")
                date_range.text = getString(R.string.date_range, "March 21 - April 19")
            }
            R.id.taurus -> {
                star_sign.text = getString(R.string.taurus)
                symbol.text = getString(R.string.symbol, "Bull")
                date_range.text = getString(R.string.date_range, "April 20 - May 20")
            }
            R.id.gemini -> {
                star_sign.text = getString(R.string.gemini)
                symbol.text = getString(R.string.symbol, "Twins")
                date_range.text = getString(R.string.date_range, "May 21 - June 20")
            }
            R.id.cancer -> {
                star_sign.text = getString(R.string.cancer)
                symbol.text = getString(R.string.symbol, "Crab")
                date_range.text = getString(R.string.date_range, "June 21 - July 22")
            }
            R.id.leo -> {
                star_sign.text = getString(R.string.capricorn)
                symbol.text = getString(R.string.symbol, "Lion")
                date_range.text = getString(R.string.date_range, "July 23 - August 22")
            }
            R.id.virgo -> {
                star_sign.text = getString(R.string.virgo)
                symbol.text = getString(R.string.symbol, "Virgin")
                date_range.text = getString(R.string.date_range, "August 23 - September 22")
            }
            R.id.libra -> {
                star_sign.text = getString(R.string.libra)
                symbol.text = getString(R.string.symbol, "Scales")
                date_range.text = getString(R.string.date_range, "September 23 - October 22")
            }
            R.id.scorpio -> {
                star_sign.text = getString(R.string.scorpio)
                symbol.text = getString(R.string.symbol, "Scorpion")
                date_range.text = getString(R.string.date_range, "October 23 - November 21")
            }
            R.id.sagittarius -> {
                star_sign.text = getString(R.string.sagittarius)
                symbol.text = getString(R.string.symbol, "Archer")
                date_range.text = getString(R.string.date_range, "November 22 - December 21")
            }
            R.id.capricorn -> {
                star_sign.text = getString(R.string.capricorn)
                symbol.text = getString(R.string.symbol, "Mountain Goat")
                date_range.text = getString(R.string.date_range, "December 22 - January 19")
            }
            else -> {
                Toast.makeText(context, getString(R.string.unknown_star_sign), Toast.LENGTH_LONG)
                    .show();
            }
        }

    }

}
