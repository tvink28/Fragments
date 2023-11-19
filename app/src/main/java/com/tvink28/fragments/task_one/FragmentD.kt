package com.tvink28.fragments.task_one

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.tvink28.fragments.R

class FragmentD : Fragment(R.layout.fragment_d) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFragmentB = view.findViewById<Button>(R.id.btnFragmentB2)

        btnFragmentB.setOnClickListener {
            (requireActivity() as GoToFragmentBButtonClickListener).onGoToFragmentBButtonClicked()
        }
    }

    companion object {
        const val FRAGMENT_D_TAG = "FRAGMENT_D_TAG"

        fun newInstance() = FragmentD()
    }
}