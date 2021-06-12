package com.fizhu.bikeappconcept.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.fizhu.bikeappconcept.App
import com.fizhu.bikeappconcept.data.models.LoginRequest
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse
import com.fizhu.bikeappconcept.utils.base.ApiError
import com.fizhu.bikeappconcept.utils.base.Resource
import com.fizhu.bikeappconcept.utils.ext.getRequest
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Created by dhruv on 07,July,2020
 * https://github.com/Fizhu
 */
open class RemoteDataSource constructor(val apiService: ApiService) {
    //REST api calling
    @SuppressLint("LogNotTimber")
    suspend fun apiLogin(otpRequest: LoginRequest): Resource<LoginResponse> {
        val response = apiService.apiLoginToapp(App.mGson.toJson(otpRequest).getRequest())
        if (response.isSuccessful) {
            return Resource.success(data = response.body()!!)
        } else {
            if (response.errorBody() != null) {
                val error = StringBuilder()
                try {
                    var bufferedReader: BufferedReader? = null
                    if (response.errorBody() != null) {
                        bufferedReader = BufferedReader(
                            InputStreamReader(
                                response.errorBody()!!.byteStream()
                            )
                        )
                        var eLine: String? = null
                        while (bufferedReader.readLine().also { eLine = it } != null) {
                            error.append(eLine)
                        }
                        bufferedReader.close()
                    }
                } catch (e: Exception) {
                    error.append(e.message)
                }
                val errorMsg = error.toString()
                val errorCode = response.code()
                val apiError = ApiError(errorMsg, errorCode)
                return Resource.error(message = apiError.message,data = null)

            } else {
                return Resource.error(message = "Something wants wrong,Try again",data = null)
            }
        }
    }
}