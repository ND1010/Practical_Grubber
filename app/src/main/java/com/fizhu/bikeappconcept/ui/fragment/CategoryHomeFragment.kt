package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EdgeEffect
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.adapters.CategoryAdapter
import com.fizhu.bikeappconcept.adapters.ItemAdapter
import com.fizhu.bikeappconcept.data.models.CategoryRaw
import com.fizhu.bikeappconcept.data.models.Item
import com.fizhu.bikeappconcept.databinding.FragmentCategoryHomeBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.observe
import com.fizhu.bikeappconcept.viewmodels.CategoryHomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoryHomeFragment : BaseFragment() {

    private val viewModel by sharedViewModel<CategoryHomeViewModel>()
    private var binding: FragmentCategoryHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_category_home, container, false
        )
        binding?.viewModel = this.viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onInit() {
        binding?.btnCheckout?.setOnClickListener {
            findNavController().navigate(R.id.action_categoryHomeFragment_to_checkoutFragment)
        }
        viewModel.getAllCategory()
        observe(viewModel.listCategory){
            loge("Category---found -> ${it.toString()}")
            initRvCategory(it)
        }

        viewModel.getAllItem()
        observe(viewModel.listItem){
            loge("Item---found -> ${it.toString()}")
        }
        initRvItem(ArrayList())
    }

    private fun initRvItem(arrayList: List<Item>) {
        val adapterItem = ItemAdapter(requireContext()) {
                /*parentFragment?.parentFragment?.findNavController()
                    ?.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))*/
            }
        binding?.recyclerViewItems?.let {
            with(it) {
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
                adapter = adapterItem

                it.edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
                    override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
                        return object : EdgeEffect(view.context) {

                            override fun onPull(deltaDistance: Float) {
                                super.onPull(deltaDistance)
                                handlePull(deltaDistance)
                            }

                            override fun onPull(deltaDistance: Float, displacement: Float) {
                                super.onPull(deltaDistance, displacement)
                                handlePull(deltaDistance)
                            }

                            private fun handlePull(deltaDistance: Float) {
                                // This is called on every touch event while the list is scrolled with a finger.
                                // We simply update the view properties without animation.
                                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                                val rotationDelta =
                                    sign * deltaDistance * com.fizhu.bikeappconcept.utils.AppConstants.OVERSCROLL_ROTATION_MAGNITUDE
                                val translationYDelta =
                                    sign * view.width * deltaDistance * com.fizhu.bikeappconcept.utils.AppConstants.OVERSCROLL_TRANSLATION_MAGNITUDE
                                view.forEachVisibleHolder { holder: ItemAdapter.ViewHolder ->
                                    holder.rotation.cancel()
                                    holder.translationY.cancel()
                                    holder.itemView.rotation += rotationDelta
                                    holder.itemView.translationY += translationYDelta
                                }
                            }

                            override fun onRelease() {
                                super.onRelease()
                                // The finger is lifted. This is when we should start the animations to bring
                                // the view property values back to their resting states.
                                view.forEachVisibleHolder { holder: ItemAdapter.ViewHolder ->
                                    holder.rotation.start()
                                    holder.translationY.start()
                                }
                            }

                            override fun onAbsorb(velocity: Int) {
                                super.onAbsorb(velocity)
                                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                                // The list has reached the edge on fling.
                                val translationVelocity =
                                    sign * velocity * com.fizhu.bikeappconcept.utils.AppConstants.FLING_TRANSLATION_MAGNITUDE
                                view.forEachVisibleHolder { holder: ItemAdapter.ViewHolder ->
                                    holder.translationY
                                        .setStartVelocity(translationVelocity)
                                        .start()
                                }
                            }
                        }
                    }
                }

                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        recyclerView.forEachVisibleHolder { holder: ItemAdapter.ViewHolder ->
                            holder.rotation
                                // Update the velocity.
                                // The velocity is calculated by the vertical scroll offset.
                                .setStartVelocity(holder.currentVelocity - dx * com.fizhu.bikeappconcept.utils.AppConstants.SCROLL_ROTATION_MAGNITUDE)
                                // Start the animation. This does nothing if the animation is already running.
                                .start()
                        }
                    }
                })

            }
        }
        adapterItem.notifyDataSetChanged()
        adapterItem.setData(arrayList)
    }

    private fun initRvCategory(listBike: List<CategoryRaw>) {
        val adapterCategory =
            CategoryAdapter(requireContext()) {
                /*parentFragment?.parentFragment?.findNavController()
                    ?.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))*/
            }
        binding?.recyclerViewCategory?.let {
            with(it) {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = adapterCategory

                it.edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
                    override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
                        return object : EdgeEffect(view.context) {

                            override fun onPull(deltaDistance: Float) {
                                super.onPull(deltaDistance)
                                handlePull(deltaDistance)
                            }

                            override fun onPull(deltaDistance: Float, displacement: Float) {
                                super.onPull(deltaDistance, displacement)
                                handlePull(deltaDistance)
                            }

                            private fun handlePull(deltaDistance: Float) {
                                // This is called on every touch event while the list is scrolled with a finger.
                                // We simply update the view properties without animation.
                                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                                val rotationDelta =
                                    sign * deltaDistance * com.fizhu.bikeappconcept.utils.AppConstants.OVERSCROLL_ROTATION_MAGNITUDE
                                val translationYDelta =
                                    sign * view.width * deltaDistance * com.fizhu.bikeappconcept.utils.AppConstants.OVERSCROLL_TRANSLATION_MAGNITUDE
                                view.forEachVisibleHolder { holder: CategoryAdapter.ViewHolder ->
                                    holder.rotation.cancel()
                                    holder.translationY.cancel()
                                    holder.itemView.rotation += rotationDelta
                                    holder.itemView.translationY += translationYDelta
                                }
                            }

                            override fun onRelease() {
                                super.onRelease()
                                // The finger is lifted. This is when we should start the animations to bring
                                // the view property values back to their resting states.
                                view.forEachVisibleHolder { holder: CategoryAdapter.ViewHolder ->
                                    holder.rotation.start()
                                    holder.translationY.start()
                                }
                            }

                            override fun onAbsorb(velocity: Int) {
                                super.onAbsorb(velocity)
                                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                                // The list has reached the edge on fling.
                                val translationVelocity =
                                    sign * velocity * com.fizhu.bikeappconcept.utils.AppConstants.FLING_TRANSLATION_MAGNITUDE
                                view.forEachVisibleHolder { holder: CategoryAdapter.ViewHolder ->
                                    holder.translationY
                                        .setStartVelocity(translationVelocity)
                                        .start()
                                }
                            }
                        }
                    }
                }

                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        recyclerView.forEachVisibleHolder { holder: CategoryAdapter.ViewHolder ->
                            holder.rotation
                                // Update the velocity.
                                // The velocity is calculated by the vertical scroll offset.
                                .setStartVelocity(holder.currentVelocity - dx * com.fizhu.bikeappconcept.utils.AppConstants.SCROLL_ROTATION_MAGNITUDE)
                                // Start the animation. This does nothing if the animation is already running.
                                .start()
                        }
                    }
                })

            }
        }
        adapterCategory.notifyDataSetChanged()
        adapterCategory.setData(listBike)
    }

    companion object {
        val TAG = CategoryHomeFragment::class.java.simpleName
    }

    inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.forEachVisibleHolder(
        action: (T) -> Unit
    ) {
        for (i in 0 until childCount) {
            action(getChildViewHolder(getChildAt(i)) as T)
        }
    }
}