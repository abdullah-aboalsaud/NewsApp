package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.SourcesResponse
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentNewsBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ApiManager.getServices()
            .getNewsSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    p0: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse", response.body()?.sources.toString())
                    }
                }

                override fun onFailure(p0: Call<SourcesResponse>, error: Throwable) {
                    Log.e("onFailure", error.localizedMessage ?: "no error")
                }

            })

    }

}