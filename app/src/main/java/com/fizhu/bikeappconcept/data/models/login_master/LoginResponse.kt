package com.fizhu.bikeappconcept.data.models.login_master


import com.fizhu.bikeappconcept.data.models.*
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("CategoryImages")
    var categoryImages: List<CategoryImage> = listOf(),
    @SerializedName("CategoryMasters")
    var categoryMasters: List<CategoryMaster> = listOf(),
    @SerializedName("Items")
    var items: List<Item> = listOf(),
    @SerializedName("ItemImages")
    var itemImages: List<ItemImage> = listOf(),
    @SerializedName("Item_Category_Mappings")
    var itemCategoryMappings: List<ItemCategoryMapping> = listOf(),
    @SerializedName("ScreenSaverMaster")
    var screenSaverMasters: List<ScreenSaverMaster> = listOf()

)