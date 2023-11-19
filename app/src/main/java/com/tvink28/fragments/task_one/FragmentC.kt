package com.tvink28.fragments.task_one

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tvink28.fragments.R

class FragmentC : Fragment(R.layout.fragment_c) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textViewFragmentC).text =
            arguments?.getString(MESSAGE_EXTRA)

        val btnFragmentA = view.findViewById<Button>(R.id.btnFragmentA)
        val btnFragmentD = view.findViewById<Button>(R.id.btnFragmentD)

        btnFragmentA.setOnClickListener {
            (requireActivity() as GoToFragmentAWithResetBackStackButtonClickListener).onGoToFragmentAButtonClicked()
        }

        btnFragmentD.setOnClickListener {
            (requireActivity() as GoToFragmentDButtonClickListener).onGoToFragmentDButtonClicked()
        }
    }

    interface GoToFragmentDButtonClickListener {
        fun onGoToFragmentDButtonClicked()
    }

    interface GoToFragmentAWithResetBackStackButtonClickListener {
        fun onGoToFragmentAButtonClicked()
    }

    companion object {
        private const val MESSAGE_EXTRA = "MESSAGE"
        const val FRAGMENT_C_TAG = "FRAGMENT_C_TAG"

        fun newInstance(value: String) = FragmentC().apply {
            arguments = Bundle().apply {
                putString(MESSAGE_EXTRA, value)
            }
        }
    }
}