package com.fizhu.bikeappconcept.data.db

import com.fizhu.bikeappconcept.data.models.*
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse
import com.fizhu.bikeappconcept.utils.ext.doBack
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.logi

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */
open class LocalDataSource constructor(
    private val screenSeverDao: ScreenSeverDao
) {
    fun getAllCategory()= screenSeverDao.allCategory()

    fun getAllItem()= screenSeverDao.allItem()

    fun insertScreenSever(list: List<ScreenSaverMaster>){
        doBack(
            action = {
                screenSeverDao.insertAllScreenSever(list)
            },
            success = { logi("success insert screen sever to db") },
            error = { loge("failed insert screen sever to db") }
        )
    }

    fun insertCategoryItem(list: List<CategoryImage>) {
        doBack(
            action = {
                screenSeverDao.insertAllCategoryImage(list)
            },
            success = { logi("success insert screen sever to db") },
            error = { loge("failed insert screen sever to db") }
        )
    }

    fun insertCategoryMaster(list: List<CategoryMaster>) {
        doBack(
            action = {
                screenSeverDao.insertAllCategoryMaster(list)
            },
            success = { logi("success insert screen sever to db") },
            error = { loge("failed insert screen sever to db") }
        )
    }

    fun insertItemCategoryMapping(list: List<ItemCategoryMapping>) {
        doBack(
            action = {
                screenSeverDao.insertAllItemCategoryMapping(list)
            },
            success = { logi("success insert screen sever to db") },
            error = { loge("failed insert screen sever to db") }
        )
    }

    fun insertItemImage(list: List<ItemImage>) {
        doBack(
            action = {
                screenSeverDao.insertAllItemImage(list)
            },
            success = { logi("success insert screen sever to db") },
            error = { loge("failed insert screen sever to db") }
        )
    }

    fun insertItem(list: List<Item>) {
        doBack(
            action = {
                screenSeverDao.insertitem(list)
            },
            success = { logi("success insert screen sever to db") },
            error = { loge("failed insert screen sever to db") }
        )
    }

}