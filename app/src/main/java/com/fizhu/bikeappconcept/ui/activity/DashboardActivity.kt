package com.fizhu.bikeappconcept.ui.activity

import android.os.Bundle
import com.fizhu.bikeappconcept.databinding.ActivityDashboardBinding
import com.fizhu.bikeappconcept.utils.base.BaseActivity

class DashboardActivity : BaseActivity() {

    companion object{
        val TAG = DashboardActivity::class.java.simpleName
    }
    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}