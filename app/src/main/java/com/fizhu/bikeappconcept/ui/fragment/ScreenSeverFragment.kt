package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentScreenSeverBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.viewmodels.CategoryHomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreenSeverFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentScreenSeverBinding
    private val viewModel by viewModel<CategoryHomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_screen_sever, container, false
        )
        return binding.root
    }

    override fun onInit() {
        binding.constraintScreenSever.setOnClickListener {
            findNavController().navigate(R.id.action_screenSeverFragment_to_categoryHomeFragment)
        }
    }

    companion object {
        val TAG = "ScreenSeverFragment"
    }
}