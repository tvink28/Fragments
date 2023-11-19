package com.tvink28.fragments.task_one

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.tvink28.fragments.R

class TaskOneActivity : AppCompatActivity(), GoToFragmentBButtonClickListener,
    FragmentB.GoToFragmentCButtonClickListener, FragmentB.GoToBackButtonClickListener,
    FragmentC.GoToFragmentAWithResetBackStackButtonClickListener,
    FragmentC.GoToFragmentDButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_one)

        if (supportFragmentManager.findFragmentByTag(FragmentA.FRAGMENT_A_TAG) == null) {
            with(supportFragmentManager.beginTransaction()) {
                replace(R.id.fragment_container_view, FragmentA.newInstance(), FragmentA.FRAGMENT_A_TAG)
                commit()
            }
        }
    }

    override fun onGoToFragmentBButtonClicked() {

        val currentFragment = supportFragmentManager.fragments.last()

        if (currentFragment is FragmentD) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            with(supportFragmentManager.beginTransaction()) {
                replace(R.id.fragment_container_view, FragmentB.newInstance(), FragmentB.FRAGMENT_B_TAG)
                addToBackStack(null)
                commit()
            }
        } else {
            with(supportFragmentManager.beginTransaction()) {
                replace(R.id.fragment_container_view, FragmentB.newInstance(), FragmentB.FRAGMENT_B_TAG)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onGoToFragmentCButtonClicked() {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.fragment_container_view, FragmentC.newInstance("Hello Fragment C"), FragmentC.FRAGMENT_C_TAG)
            addToBackStack(null)
            commit()
        }
    }

    override fun onGoToBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }

    override fun onGoToFragmentDButtonClicked() {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.fragment_container_view, FragmentD.newInstance(), FragmentD.FRAGMENT_D_TAG)
            addToBackStack(null)
            commit()
        }
    }

    override fun onGoToFragmentAButtonClicked() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.fragment_container_view, FragmentA.newInstance(), FragmentA.FRAGMENT_A_TAG)
            commit()
        }
    }
}