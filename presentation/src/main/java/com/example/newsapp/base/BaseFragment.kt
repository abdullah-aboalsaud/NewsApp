package com.example.newsapp.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.newsapp.R
import com.example.newsapp.utils.showDialog


abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private var _binding: VB? = null
    open val binding get() = _binding!!
    private var progressDialog: ProgressDialog? = null

    lateinit var viewModel: VM

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun initViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        observeMessage()
    }

    private fun observeMessage() {
        viewModel.uiMessage.observe(viewLifecycleOwner) { uiMessage ->
            handelMessage(uiMessage)
        }
    }


    open fun showLoadingDialog(
        @StringRes messageId: Int, isCancelable: Boolean? = null
    ) {
        progressDialog?.setMessage(getString(messageId))
        progressDialog?.setCancelable(isCancelable ?: true)
        progressDialog?.show()
    }

    open fun hideLoadingDialog() {
        progressDialog?.dismiss()
    }

    fun handelMessage(uiMessage: UiMessage) {
        if (uiMessage.showLoading == true) {
            uiMessage.messageId?.let {
                showLoadingDialog(
                    messageId = it, isCancelable = uiMessage.isCancelable
                )
            }
            uiMessage.messageId?.let {
                showLoadingDialog(
                    messageId = it, isCancelable = uiMessage.isCancelable
                )
            }
            uiMessage.messageId?.let {
                showLoadingDialog(
                    messageId = it, isCancelable = uiMessage.isCancelable
                )
            }
        }
        if (uiMessage.showLoading == false) {
            hideLoadingDialog()
        }
        if (uiMessage.showMessage == true) {
            requireContext().showDialog(
                messageId = uiMessage.messageId ?: R.string.something_went_wrong,
                message = uiMessage.message,
                posBtnTextId = uiMessage.posBtnId ?: R.string.ok,
                negBtnTextId = uiMessage.negBtnId,
                onPositiveClick = uiMessage.onPositiveClick,
                onNegativeClick = uiMessage.onNegativeClick,
                isCancelable = uiMessage.isCancelable
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}