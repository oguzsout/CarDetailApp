package com.oguzdogdu.carapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oguzdogdu.carapp.R
import com.oguzdogdu.carapp.databinding.CarRowBinding
import com.oguzdogdu.carapp.model.ModelItem
import com.oguzdogdu.carapp.view.FeedFragmentDirections


class CarAdapter(val carList: ArrayList<ModelItem>) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    class CarViewHolder(var viewItem: CarRowBinding) : RecyclerView.ViewHolder(viewItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CarRowBinding>(inflater, R.layout.car_row, parent, false)
        return CarViewHolder(view)

    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        holder.viewItem.car = carList[position]
        holder.itemView.rootView.setOnClickListener {
            val action =
                FeedFragmentDirections.actionFeedFragmentToDetailFragment(carList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun updateList(newCarList: List<ModelItem>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }

}











