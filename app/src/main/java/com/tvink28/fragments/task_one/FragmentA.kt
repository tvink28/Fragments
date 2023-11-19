package com.tvink28.fragments.task_one

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.tvink28.fragments.R

class FragmentA : Fragment(R.layout.fragment_a) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnFragmentB).setOnClickListener {
            (requireActivity() as GoToFragmentBButtonClickListener).onGoToFragmentBButtonClicked()
        }
    }

    companion object {
        const val FRAGMENT_A_TAG = "FRAGMENT_A_TAG"

        fun newInstance() = FragmentA()
    }
}