package com.android.testable.viewmodellivedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_split_2.*

class SplitFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_split_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val totalsViewModel = ViewModelProviders.of(requireActivity()).get(TotalsViewModel::class.java)
        totalsViewModel.getTotal().observe(viewLifecycleOwner, Observer {
            updateText(it)
        })
    }

    private fun updateText(total: Int) {
        fragment_split_2_text_view.text = getString(R.string.total, total)
    }
}