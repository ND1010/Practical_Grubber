package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.data.models.CategoryRaw
import com.fizhu.bikeappconcept.databinding.RawCategoryBinding
import com.fizhu.bikeappconcept.utils.base.Utils.Companion.IMAGE_BASE
import com.fizhu.bikeappconcept.utils.ext.loge


class CategoryAdapter(
    private val context: Context,
    private val callBack: (category: CategoryRaw) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val list: MutableList<CategoryRaw> = mutableListOf()

    fun setData(listBike: List<CategoryRaw>) {
        list.clear()
        list.addAll(listBike)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RawCategoryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RawCategoryBinding.bind(view)

        var currentVelocity = 0f

        val rotation: SpringAnimation = SpringAnimation(view, SpringAnimation.ROTATION)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
            .addUpdateListener { _, _, velocity ->
                currentVelocity = velocity
            }

        val translationY: SpringAnimation = SpringAnimation(view, SpringAnimation.TRANSLATION_Y)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder.binding) {
            tvName.text = data.categoryMaster?.name
            setImage("${IMAGE_BASE}${data.categoryImage?.imageUrl ?: ""}", ivItem)
            cardView.setOnClickListener { callBack.invoke(data) }
        }
    }

    private fun setImage(url: String, iv: ImageView) {
        loge("URL -> $url")
        Glide.with(context)
            //.asBitmap()
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(iv)
    }
}