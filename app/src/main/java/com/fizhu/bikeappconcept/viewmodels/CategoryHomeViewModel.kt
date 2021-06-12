package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.*
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.route

class CategoryHomeViewModel(
    private val repository: Repository
) : BaseViewModel() {
    val listCategory: MutableLiveData<List<CategoryRaw>> = MutableLiveData()
    fun getAllCategory() {
        compositeDisposable.route(repository.getAllCategory(),
            io = {
                if (it.isNotEmpty()) {
                    listCategory.postValue(it)
                } else {
                    listCategory.postValue(null)
                }
            },
            error = {
                listCategory.postValue(null)
                loge(it.localizedMessage)
            })
    }

    val listItem: MutableLiveData<List<ItemWithImage>> = MutableLiveData()
    fun getAllItem() {
        compositeDisposable.route(repository.getAllItem(),
            io = {
                if (it.isNotEmpty()) {
                    listItem.postValue(it)
                } else {
                    listItem.postValue(null)
                }
            },
            error = {
                listItem.postValue(null)
                loge(it.localizedMessage)
            })
    }
}