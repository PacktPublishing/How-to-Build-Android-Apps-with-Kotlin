package com.example.jetpackfragments

import android.os.Bundle
import androidx.navigation.NavDirections

class StarSignListFragmentDirections private constructor() {
    private data class StarSignListFragmentDirections(val starSignId: Int = UNKNOWN_STAR_SIGN_ID) :
        NavDirections {
        override fun getActionId(): Int = R.id.action_starSignList_to_starSign

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putInt(STAR_SIGN_ID, this.starSignId)
            return result
        }
    }

    companion object {
        fun actionStarSignListToStarSignDetail(starSignId: Int = UNKNOWN_STAR_SIGN_ID): NavDirections =
            StarSignListFragmentDirections(starSignId)
    }
}