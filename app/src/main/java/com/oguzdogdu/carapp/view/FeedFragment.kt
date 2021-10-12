package com.oguzdogdu.carapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.carapp.R
import com.oguzdogdu.carapp.adapter.CarAdapter
import com.oguzdogdu.carapp.databinding.FragmentFeedBinding
import com.oguzdogdu.carapp.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val carAdapter = CarAdapter(arrayListOf())
    private lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.carList.layoutManager = LinearLayoutManager(context)
        binding.carList.adapter = carAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.carList.visibility = View.GONE
            binding.carError.visibility = View.GONE
            binding.carLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

    }
    private fun observeLiveData() {
        viewModel.cars.observe(viewLifecycleOwner, Observer { cars ->

            cars?.let {
                binding.carList.visibility = View.VISIBLE
                carAdapter.updateList(cars)

            }

        })

        viewModel.carError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.carError.visibility = View.VISIBLE
                } else {
                    binding.carError.visibility = View.GONE
                }
            }
        })

        viewModel.carLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.carLoading.visibility = View.VISIBLE
                    binding.carList.visibility = View.GONE
                    binding.carError.visibility = View.GONE
                } else {
                    binding.carLoading.visibility = View.GONE
                }
            }
        })

    }



}

















