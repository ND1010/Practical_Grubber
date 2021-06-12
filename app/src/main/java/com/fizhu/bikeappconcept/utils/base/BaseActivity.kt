package com.fizhu.bikeappconcept.utils.base

import androidx.appcompat.app.AppCompatActivity
import com.fizhu.bikeappconcept.utils.ext.toast

abstract class BaseActivity: AppCompatActivity() {

    fun onUnexpectedError() {
        toast("Error")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}