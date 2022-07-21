package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.shoe_list_item.view.*

class ShoeListFragment : Fragment() {

    //private val viewModel: ShoeShopViewModel by activityViewModels()
    private lateinit var viewModel: ShoeShopViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }
        // Link the UI Controller and ShoeShopViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ShoeShopViewModel::class.java)

        //Observe the Subject (List of shoes) and then add the new items as view using addView method
        viewModel.shoeItemsList.observe(viewLifecycleOwner, Observer { shoesList ->
            shoesList.forEach {
                val myLayout = binding.showListLayout
                val view = LayoutInflater.from(requireContext()).inflate(R.layout.shoe_list_item,myLayout,false)
                view.shoes_size_data.text = it.size
                view.shoes_name_data.text = it.name.uppercase()
                view.shoe_company_data.text = it.company
                view.shoe_description_data.text = it.description
                myLayout.addView(view)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
    //These methods are for overflow menu with logout item
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment2())
                viewModel.resetShoeList()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}