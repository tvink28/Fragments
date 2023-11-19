package com.tvink28.fragments.task_two

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.tvink28.fragments.R
import com.tvink28.fragments.task_two.ListUsersFragment.Companion.FRAGMENT_LIST_USERS_TAG

class TaskTwoActivity : AppCompatActivity(R.layout.activity_task_two),
    ListUsersFragment.UserEditListener, EditUserFragment.SaveUserListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentByTag(FRAGMENT_LIST_USERS_TAG) == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view_user_list, ListUsersFragment.newInstance(), FRAGMENT_LIST_USERS_TAG)
            }
        }
    }

    override fun onOpenEditUserFragment(user: User) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container_view_user_list,
                EditUserFragment.newInstance(user),
                EditUserFragment.FRAGMENT_EDIT_USER_TAG
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onSetFragmentResultListener(callback: (User) -> Unit) {
        supportFragmentManager.setFragmentResultListener(
            "edit_user_request_key",
            this
        ) { _, bundle ->
            val updatedUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable("updated_user", User::class.java)
            } else {
                bundle.getSerializable("updated_user") as User
            }
            updatedUser?.let { callback(it) }
        }
    }

    override fun onSaveUserListener(user: User) {
        supportFragmentManager.setFragmentResult(
            "edit_user_request_key",
            bundleOf("updated_user" to user)
        )
        supportFragmentManager.popBackStack()
    }
}