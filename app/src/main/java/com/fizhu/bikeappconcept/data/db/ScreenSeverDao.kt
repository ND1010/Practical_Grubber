package com.fizhu.bikeappconcept.data.db

import androidx.room.*
import com.fizhu.bikeappconcept.data.models.*
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse
import io.reactivex.Observable

@Dao
interface ScreenSeverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllScreenSever(screenSever: List<ScreenSaverMaster>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategoryImage(screenSever: List<CategoryImage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategoryMaster(list: List<CategoryMaster>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemCategoryMapping(list: List<ItemCategoryMapping>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemImage(list: List<ItemImage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertitem(list: List<Item>)

    @get:Query("SELECT * FROM screen_saver_master")
    val all: Observable<List<ScreenSaverMaster>>

    @Transaction
    @Query("SELECT * FROM category_master")
    fun allCategory(): Observable<List<CategoryRaw>>

    @Transaction
    @Query("SELECT * FROM item")
    fun allItem(): Observable<List<ItemWithImage>>

}