package com.example.newsapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.newsapp.R
import com.example.newsapp.utils.fromJson
import com.example.newsapp.utils.showDialog
import retrofit2.Response


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    open val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun <T> handelError(response: Response<T>) {
        val errorResponse = response.errorBody()?.string()?.fromJson(BaseResponse::class.java)
        requireContext().showDialog(
            message = errorResponse?.message
                ?: R.string.something_went_wrong.toString(),
            positiveBtnText = R.string.ok.toString()
        )
    }

}