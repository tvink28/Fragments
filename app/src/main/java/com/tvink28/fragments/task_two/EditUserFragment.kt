package com.tvink28.fragments.task_two

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.tvink28.fragments.R

class EditUserFragment : Fragment(R.layout.fragment_edit_user), TextWatcher {

    private lateinit var user: User
    private lateinit var btnSave: Button

    private lateinit var firstNameInputLayout: TextInputLayout
    private lateinit var lastNameInputLayout: TextInputLayout
    private lateinit var phoneInputLayout: TextInputLayout

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var phoneEditText: EditText

    private lateinit var firstNameText: String
    private lateinit var lastNameText: String
    private lateinit var phoneNumberText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            firstNameEditText = findViewById(R.id.inputEditFirstName)
            lastNameEditText = findViewById(R.id.inputEditLastName)
            phoneEditText = findViewById(R.id.inputEditPhone)
            firstNameInputLayout = findViewById(R.id.inputLayoutFirstName)
            lastNameInputLayout = findViewById(R.id.inputLayoutLastName)
            phoneInputLayout = findViewById(R.id.inputLayoutPhone)
        }

        btnSave = view.findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            (requireActivity() as SaveUserListener).onSaveUserListener(saveUser())
        }

        firstNameEditText.addTextChangedListener(this)
        lastNameEditText.addTextChangedListener(this)
        phoneEditText.addTextChangedListener(this)

        firstNameEditText.setText(user.firstName)
        lastNameEditText.setText(user.lastName)
        phoneEditText.setText(user.phoneNumber)

    }

    override fun beforeTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        firstNameText = firstNameEditText.text.toString()
        lastNameText = lastNameEditText.text.toString()
        phoneNumberText = phoneEditText.text.toString()

        when (s) {
            firstNameEditText.text -> showError(firstNameEditText, firstNameInputLayout, R.string.error_first_name)
            lastNameEditText.text -> showError(lastNameEditText, lastNameInputLayout, R.string.error_last_name)
            phoneEditText.text -> showError(phoneEditText, phoneInputLayout, R.string.error_phone_number)
        }
        btnSave.isEnabled = isNotEmpty()
    }

    override fun afterTextChanged(s: Editable?) {}

    private fun isNotEmpty() =
        firstNameText.isNotEmpty() && lastNameText.isNotEmpty() && phoneNumberText.isNotEmpty()

    private fun showError(
        editText: EditText,
        inputLayout: TextInputLayout,
        errorMessageResId: Int
    ) {
        inputLayout.error =
            editText.text.toString().takeIf { it.isBlank() }?.let { getString(errorMessageResId) }
    }

    private fun saveUser(): User {
        firstNameText = firstNameEditText.text.toString()
        lastNameText = lastNameEditText.text.toString()
        phoneNumberText = phoneEditText.text.toString()

        return User(user.id, user.photo, firstNameText, lastNameText, phoneNumberText)
    }

    interface SaveUserListener {
        fun onSaveUserListener(user: User)
    }

    companion object {
        const val FRAGMENT_EDIT_USER_TAG = "FRAGMENT_EDIT_USER_TAG"

        @JvmStatic
        fun newInstance(user: User) =
            EditUserFragment().apply {
                this.user = user
            }
    }
}