package com.tvink28.fragments.task_one

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.tvink28.fragments.R

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFragmentC = view.findViewById<Button>(R.id.btnFragmentC)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        btnFragmentC.setOnClickListener {
            (requireActivity() as GoToFragmentCButtonClickListener).onGoToFragmentCButtonClicked()
        }

        btnBack.setOnClickListener {
            (requireActivity() as GoToBackButtonClickListener).onGoToBackButtonClicked()
        }
    }

    interface GoToFragmentCButtonClickListener {
        fun onGoToFragmentCButtonClicked()
    }

    interface GoToBackButtonClickListener {
        fun onGoToBackButtonClicked()
    }

    companion object {
        const val FRAGMENT_B_TAG = "FRAGMENT_B_TAG"

        fun newInstance() = FragmentB()
    }
}