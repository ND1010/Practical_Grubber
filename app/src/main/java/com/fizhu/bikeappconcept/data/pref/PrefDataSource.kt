package com.fizhu.bikeappconcept.data.pref

class PrefDataSource {
    fun getIsLogin() : Boolean = UserSession.keyIsLogin
    fun setIsLogin(isLogin: Boolean) {
        UserSession.keyIsLogin = isLogin
    }
    fun getId() : String? = UserSession.keyId
    fun setId(id: String) {
        UserSession.keyId = id
    }
}