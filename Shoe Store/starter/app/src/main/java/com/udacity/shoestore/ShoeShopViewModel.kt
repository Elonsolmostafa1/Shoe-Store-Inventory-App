package com.udacity.shoestore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeShopViewModel : ViewModel(){
    private val _shoeItemsList = MutableLiveData<MutableList<Shoe>>()
    val shoeItemsList : LiveData<MutableList<Shoe>> get() = _shoeItemsList
    var shoeObject = Shoe("","","","")

    init {
        _shoeItemsList.value = mutableListOf()
    }

    fun onSaveClick(newShoeObject:Shoe){
        _shoeItemsList.value?.add(newShoeObject)
        shoeObject = Shoe("","","","")
    }



}