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

        //Connecting UIController and ShoeShopViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ShoeShopViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.shoeObject

        //Save and Cancel buttons clickListeners
        binding.saveButton.setOnClickListener { onSaveButtonClickedValidateAndNavigate() }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
            binding.enterDescription.text.clear()
            binding.enterSize.text.clear()
            binding.enterName.text.clear()
            binding.enterCompany.text.clear()

        }

        return binding.root
    }

    /**
     * This function will ensure that all editText fields are not empty when save button is clicked
     * is any field is empty it will pop up a Toast asking to enter the missing field
     * if every thing is ok it will and this item to the list and navigate to the ShoeList fragment
     */
    fun onSaveButtonClickedValidateAndNavigate(){
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
}