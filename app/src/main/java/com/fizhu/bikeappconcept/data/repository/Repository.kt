package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.models.*
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse
import com.fizhu.bikeappconcept.utils.base.Resource
import io.reactivex.Observable

interface Repository {
    fun getIsLogin(): Boolean
    fun setIsLogin(isLogin: Boolean)
    fun getId(): String?
    fun setId(id: String)

    fun insertScreenSever(list: List<ScreenSaverMaster>)
    fun insertCategoryMasters(list: List<CategoryMaster>)
    fun insertCategoryImage(list: List<CategoryImage>)
    fun insertItems(list: List<Item>)
    fun insertItemImages(list: List<ItemImage>)
    fun insertItemCategoryMapping(list: List<ItemCategoryMapping>)
    fun getAllCategory(): Observable<List<CategoryRaw>>
    fun getAllItem(): Observable<List<ItemWithImage>>

    suspend fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse>
}