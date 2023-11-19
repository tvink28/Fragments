package com.tvink28.fragments.task_two

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tvink28.fragments.R
import io.github.serpro69.kfaker.Faker

class ListUsersFragment : Fragment(R.layout.fragment_list_users) {

    private lateinit var recyclerView: RecyclerView

    private val userListLiveData = MutableLiveData<List<User>?>(generateContacts())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListUsersAdapter { user ->
            (requireActivity() as UserEditListener).onOpenEditUserFragment(user)
        }
        recyclerView = view.findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        userListLiveData.observe(viewLifecycleOwner) { adapter.submitList(it) }

        (requireActivity() as UserEditListener).onSetFragmentResultListener { result ->
            onContactSaved(result)
        }
    }

    private fun onContactSaved(user: User) {
        val list = userListLiveData.value?.toMutableList()

        user.let {
            val existingIndex = list?.indexOfFirst {
                it.id == user.id
            }
            existingIndex?.let { itIndex -> list.set(itIndex, it) }
        }
        userListLiveData.value = list
    }

    private fun generateContacts(): MutableList<User> {
        val faker = Faker()
        val listContact = mutableListOf<User>()
        val listPhoto = listOf(DOG, PARIS, RIO, "")

        for (i in 1..4) {
            val contact = User(
                id = i,
                photo = URL + listPhoto[i - 1],
                firstName = faker.name.firstName(),
                lastName = faker.name.lastName(),
                phoneNumber = faker.phoneNumber.phoneNumber()
            )
            listContact.add(contact)
        }
        return listContact
    }

    interface UserEditListener {
        fun onOpenEditUserFragment(user: User)
        fun onSetFragmentResultListener(callback: (User) -> Unit)
    }

    companion object {
        const val FRAGMENT_LIST_USERS_TAG = "FRAGMENT_LIST_USERS_TAG"
        private const val URL = "https://loremflickr.com/320/240/"
        private const val DOG = "dog"
        private const val PARIS = "paris,girl/all"
        private const val RIO = "brazil,rio"

        fun newInstance() = ListUsersFragment()
    }
}