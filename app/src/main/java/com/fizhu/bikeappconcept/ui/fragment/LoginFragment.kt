package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentLoginBinding
import com.fizhu.bikeappconcept.ui.activity.AuthActivity
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.base.Status
import com.fizhu.bikeappconcept.utils.base.Utils
import com.fizhu.bikeappconcept.utils.ext.doExitApp
import com.fizhu.bikeappconcept.utils.ext.observe
import com.fizhu.bikeappconcept.utils.ext.toast
import com.fizhu.bikeappconcept.viewmodels.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding3.widget.textChangeEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment() {

    private val viewModel by viewModel<LoginViewModel>()
    private var binding: FragmentLoginBinding? = null
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }
    private var isUsernameValid = false
    private var isPasswordValid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        binding?.viewModel = this.viewModel
        return binding?.root
    }

    override fun onInit() {
        binding?.btnLogin?.isEnabled = false
        handleBackPressed()
        onClick()
        initValidation()
        observe(viewModel.isLoggedIn) {
            if (it) {
                (activity as AuthActivity).initHome()
            } else {
                requireContext().toast("Login failed, username or password invalid")
            }
            requireActivity().toast("Login Done")
        }
    }

    private fun onClick() {
//        binding?.tvSignup?.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
        binding?.btnLogin?.setOnClickListener {
            observe(viewModel.doLoginToApp()) {
                it.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Utils.hideProgressDialogApp()
                            binding?.btnLogin?.isEnabled = true
                            viewModel.insertAllData(it.data)
                        }
                        Status.ERROR -> {
                            Utils.hideProgressDialogApp()
                            binding?.btnLogin?.isEnabled = true

                        }
                        Status.LOADING -> {
                            Utils.showProgressDialogApp(requireActivity(),getString(R.string.login_in_process))
                            binding?.btnLogin?.isEnabled = false
                        }
                    }
                }
            }
        }
    }

    private fun initValidation() {
        validation(binding?.etUsername!!, binding?.tilUsername!!, 0)
        validation(binding?.etPw!!, binding?.tilPw!!, 1)
    }

    private fun validation(et: TextInputEditText, til: TextInputLayout, type: Int) {
        val observable = et.textChangeEvents()
        compositeDisposable.add(observable
            .skip(1)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map { it.text }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.length < 4) {
                    til.isHelperTextEnabled = true
                    til.helperText = getString(R.string.invalid_validation)
                    when (type) {
                        0 -> {
                            isUsernameValid = false
                        }
                        1 -> {
                            isPasswordValid = false
                        }
                    }
                } else {
                    when (type) {
                        0 -> {
                            isUsernameValid = true
                            til.isHelperTextEnabled = false
                        }
                        1 -> {
                            isPasswordValid = true
                            til.isHelperTextEnabled = false
                        }
                    }
                }

            }
            .subscribe { binding?.btnLogin?.isEnabled = isUsernameValid && isPasswordValid })
    }

    private fun handleBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                doExitApp(requireActivity())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.etUsername?.text?.clear()
        binding?.etPw?.text?.clear()
        compositeDisposable.clear()
    }
}