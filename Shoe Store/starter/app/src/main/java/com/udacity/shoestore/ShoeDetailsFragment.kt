package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : Fragment() {
    lateinit var viewModel: ShoeShopViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeShopViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.shoeObject
        binding.saveButton.setOnClickListener {
            if (viewModel.shoeObject.name.trim().isNullOrEmpty() ||
                viewModel.shoeObject.company.trim().isNullOrEmpty() ||
                viewModel.shoeObject.size.trim().isNullOrEmpty() ||
                viewModel.shoeObject.description.trim().isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please enter all required information", Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.onSaveClick(viewModel.shoeObject)
                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
        }

        return binding.root
    }
}