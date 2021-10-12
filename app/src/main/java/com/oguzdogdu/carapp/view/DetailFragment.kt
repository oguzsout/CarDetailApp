package com.oguzdogdu.carapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.oguzdogdu.carapp.R
import com.oguzdogdu.carapp.databinding.FragmentDetailBinding
import com.oguzdogdu.carapp.viewmodel.DetailViewModel


class DetailFragment : Fragment() {

    private lateinit var viewModel : DetailViewModel
    private lateinit var dataBinding : FragmentDetailBinding
    private var carUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            carUuid = DetailFragmentArgs.fromBundle(it).carUuid
        }


        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(carUuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.carLiveData.observe(viewLifecycleOwner, Observer { car ->
            car?.let {
                dataBinding.selectedCar = car
            }
        } )
    }


}

