package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.fizhu.bikeappconcept.data.models.LoginRequest
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.base.Resource
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.route
import kotlinx.coroutines.Dispatchers


class LoginViewModel(
    private val repository: Repository
) : BaseViewModel() {
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    private val _isLoggedIn = SingleLiveEvent<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun doLoginToApp() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val otpRequest = LoginRequest(
                "1.0",
                "c4db5cb7e4a46365",
                "1",
                "ef5Dk7VIGsU:APA91bHhPJLrfv2oLhMCT8geh1v0sZGZyvtGXMvU_AAV6ncep3O_g1po0n1Wy_ACNm5HLv6KEjJgZ8I-SNJZnZ5wp1Bsz_QQi82YUTy8-0IwctLICHz_Zy49NBV0OowslifNcgrahad5",
                "1",
                password.value.toString(),
                username.value.toString()
            )
            emit(repository.doLogin(otpRequest))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insertAllData(resource: LoginResponse?) {
        resource?.let {
            //Insert the ScreenSever list
            repository.insertScreenSever(it.screenSaverMasters)
            repository.insertItems(it.items)
            repository.insertItemImages(it.itemImages)
            repository.insertItemCategoryMapping(it.itemCategoryMappings)
            repository.insertCategoryMasters(it.categoryMasters)
            repository.insertCategoryImage(it.categoryImages)
            repository.setIsLogin(true)
            _isLoggedIn.postValue(true)
        }
    }

    /*private fun getErrorMessageFromGenericResponse(httpException: HttpException): String? {
        var errorMessage: String? = null
        try {
            val body = httpException.response()?.errorBody()
            val adapter = Gson().getAdapter(GenericResponse::class.java)
            val errorParser = adapter.fromJson(body?.string())
            errorMessage = errorParser.errorMessage.get(0)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            return errorMessage
        }
    }*/

}