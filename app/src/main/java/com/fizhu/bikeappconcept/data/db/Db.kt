package com.fizhu.bikeappconcept.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fizhu.bikeappconcept.data.models.*
import com.fizhu.bikeappconcept.data.models.login_master.LoginResponse


/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */
@Database(
    entities = [ScreenSaverMaster::class, Item::class, ItemImage::class, CategoryImage::class, CategoryMaster::class,
        ItemCategoryMapping::class],
    version = 1, exportSchema = false
)
abstract class Db : RoomDatabase() {

    // --- DAO ---
    abstract fun screenServerDao(): ScreenSeverDao

    companion object {

        // --- SINGLETON ---
        @Volatile
        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Db::class.java,
                    "bike_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}