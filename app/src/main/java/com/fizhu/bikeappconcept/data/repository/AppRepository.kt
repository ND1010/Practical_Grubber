package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.db.LocalDataSource
import com.fizhu.bikeappconcept.data.models.*
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse
import com.fizhu.bikeappconcept.data.pref.PrefDataSource
import com.fizhu.bikeappconcept.utils.base.Resource
import io.reactivex.Observable

open class AppRepository constructor(
    private val db: LocalDataSource,
    private val pref: PrefDataSource,
    private val remoteRepo: RemoteDataSource
) : Repository {

    override fun getIsLogin(): Boolean = pref.getIsLogin()
    override fun setIsLogin(isLogin: Boolean) = pref.setIsLogin(isLogin)
    override fun getId(): String? = pref.getId()
    override fun setId(id: String) = pref.setId(id)

    override fun insertScreenSever(list: List<ScreenSaverMaster>)= db.insertScreenSever(list)
    override fun insertCategoryImage(list: List<CategoryImage>)  = db.insertCategoryItem(list)
    override fun insertCategoryMasters(list: List<CategoryMaster>) = db.insertCategoryMaster(list)
    override fun insertItemCategoryMapping(list: List<ItemCategoryMapping>) = db.insertItemCategoryMapping(list)
    override fun insertItemImages(list: List<ItemImage>) = db.insertItemImage(list)
    override fun insertItems(list: List<Item>)  = db.insertItem(list)
    override fun getAllCategory(): Observable<List<CategoryRaw>> = db.getAllCategory()
    override fun getAllItem(): Observable<List<ItemWithImage>> = db.getAllItem()

    override suspend fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> = remoteRepo.apiLogin(loginRequest)
}