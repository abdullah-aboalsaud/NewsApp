package com.example.newsapp.ui.fragments.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.api.model.SourcesResponse
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentSportsBinding
import com.example.newsapp.utils.showDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SportsFragment : BaseFragment<FragmentSportsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSportsBinding {
        return FragmentSportsBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsSources()


    }

    private fun getNewsSources() {
        ApiManager.getServices()
            .getNewsSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    p0: Call<SourcesResponse>, response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        showNewsSources(response.body()?.sources)
                    } else {

                        handelError(response)

                    }
                }

                override fun onFailure(p0: Call<SourcesResponse>, error: Throwable) {
                    Log.e("onFailure", error.localizedMessage ?: "no error")
                    requireContext().showDialog(
                        message = getString(R.string.something_went_wrong),
                        positiveBtnText = getString(R.string.retry),
                        onPositiveClick = {
                            getNewsSources()
                        }
                    )

                }

            })
    }


    private fun showNewsSources(sources: List<SourcesItem?>?) {

    }


}