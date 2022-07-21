package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login,
            container, false
        )

        // Login and Sign up clickListeners
        binding.loginButton.setOnClickListener { validateEmailAndPassword() }

        binding.signUpButton.setOnClickListener { validateEmailAndPassword() }

        return binding.root
    }

    // function with small check that the email and password are not empty
    fun validateEmailAndPassword(){
        if(binding.enterEmail.text.trim().isNullOrEmpty() || binding.enterPassword.text.trim().isNullOrEmpty())
            Toast.makeText(requireContext(),"Please enter the missing info",Toast.LENGTH_LONG).show()
        else
            findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToWelcomeFragment())

    }


}