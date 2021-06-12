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
import com.fizhu.bikeappconcept.data.models.Item
import com.fizhu.bikeappconcept.databinding.RawItemBinding


class ItemAdapter(
    private val context: Context,
    private val callBack: (bike: Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val list: MutableList<Item> = mutableListOf()

    fun setData(listBike: List<Item>) {
        list.clear()
        list.addAll(listBike)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RawItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = 10/*list.size*/

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RawItemBinding.bind(view)
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
        //val data = list[position]
        with(holder.binding) {
//            tvName.text = data.name
//            setImage(data.image?:"", ivBike)
//            cardView.setOnClickListener { callBack.invoke(data) }
        }
    }

    private fun setImage(url: String, iv: ImageView) {
        Glide.with(context)
            .asBitmap()
            .load(context.resources.getIdentifier(url, "drawable", context.packageName))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(iv)
    }
}