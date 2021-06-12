package com.fizhu.bikeappconcept.data.models

import androidx.annotation.Nullable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*Data start*/
@Entity(tableName = "screen_saver_master")
data class ScreenSaverMaster(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ScreenSaverID")
    @Expose
    @SerializedName("ScreenSaverID")
    var screenSaverID: Int? = 0,
    @Expose
    @SerializedName("ImagePath")
    var imagePath: String? = null
)


data class CategoryRaw(
    @Embedded
    val categoryMaster: CategoryMaster?,
    @Relation(
        parentColumn = "CategoryID",
        entityColumn = "Cat_ID"
    )
    @Nullable
    val categoryImage: CategoryImage?
){
    override fun toString(): String {
        return "CategoryRaw(categoryMaster=$categoryMaster, albums=$categoryImage)"
    }
}


@Entity(
    tableName = "category_image", foreignKeys = arrayOf(
        ForeignKey(
            entity = CategoryMaster::class,
            parentColumns = arrayOf("CategoryID"),
            childColumns = arrayOf("Cat_ID"),
            onDelete = CASCADE
        )
    )
)
data class CategoryImage(
    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CImgID")
    @SerializedName("CImgID")
    var cImgID: Int?,
    @Nullable
    @ColumnInfo(name = "Cat_ID")
    @SerializedName("CategoryID")
    var categoryID: Int?,
    @Nullable
    @SerializedName("ImageUrl")
    var imageUrl: String?
)


@Entity(tableName = "category_master")
data class CategoryMaster(
    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CategoryID")
    @SerializedName("CategoryID")
    var categoryID: Int?,
    @Nullable
    @SerializedName("Name")
    var name: String?,
    @Nullable
    @SerializedName("Description")
    var description: String?
)

@Entity(tableName = "item_category_mapping")
data class ItemCategoryMapping(
    @PrimaryKey(autoGenerate = true)
    @Nullable
    @ColumnInfo(name = "PCMappingID")
    @SerializedName("PCMappingID")
    var pCMappingID: Int?,
    @Nullable
    @SerializedName("CategoryID")
    var categoryID: String?,
    @Nullable
    @SerializedName("DisplayOrder")
    var displayOrder: String?,
    @Nullable
    @SerializedName("ItemID")
    var itemID: String?
)


/*@Entity(primaryKeys = ["PCMappingID", "ItemID"])
data class ItemListCrossRef(
    @Nullable
    val PCMappingID: Int?,
    @Nullable
    val ItemID: Int?
)*/


/*data class ItemlistWithCategory(

    @Embedded val itemCategoryMapping: ItemCategoryMapping,
    @Relation(
        parentColumn = "PCMappingID",
        entityColumn = "ItemID",
        associateBy = Junction(ItemListCrossRef::class)
    )
    @Nullable
    val songs: List<Item>
)*/


@Entity(
    tableName = "item_image", foreignKeys = arrayOf(
        ForeignKey(
            entity = Item::class,
            parentColumns = arrayOf("ItemID"),
            childColumns = arrayOf("ItemID"),
            onDelete = CASCADE
        )
    )
)
data class ItemImage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IImgID")
    @SerializedName("IImgID")
    @Nullable
    var iImgID: Int?,
    @Nullable
    @SerializedName("ImageUrl")
    var imageUrl: String?,
    @Nullable
    @ColumnInfo(name = "ItemID")
    @SerializedName("ItemID")
    var itemID: String?
)


@Entity(tableName = "item")
data class Item(
    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ItemID")
    @SerializedName("ItemID")
    var itemID: Int?,
    @Nullable
    @SerializedName("Name")
    var name: String?,
    @Nullable
    @SerializedName("FullDescription")
    var fullDescription: String?,
    @Nullable
    @SerializedName("Price")
    var price: String?
)


data class ItemWithImage(
    @Embedded
    val item: Item?,
    @Relation(
        parentColumn = "ItemID",
        entityColumn = "ItemID")
    @Nullable
    val itemImage: ItemImage?
)

/*Data end*/
