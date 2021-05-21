package com.tapdevs.core.ui.fragments

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract fun getErrorView(): View

    abstract fun getErrorMessageTextView(): TextView

    abstract fun getSuccessView(): View

    abstract fun getWaitingView(): View

    fun showProgress() = progressStatus(View.VISIBLE)

    private fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) {
        getWaitingView().visibility = viewStatus
        if(viewStatus == View.VISIBLE){
            hideError()
            hideSuccessResponseView()
        }

    }

    fun showError(message: String) {
        getErrorMessageTextView().text = message
        errorStatus(View.VISIBLE)
    }

    private fun hideError() = errorStatus(View.GONE)

    private fun errorStatus(viewStatus: Int) {
        getErrorView().visibility = viewStatus
        if(viewStatus == View.VISIBLE){
            hideProgress()
            hideSuccessResponseView()
        }
    }

    fun showSuccessResponseView() = successReponseStatus(View.VISIBLE)

    private fun hideSuccessResponseView() = errorStatus(View.GONE)

    private fun successReponseStatus(viewStatus: Int) {
        getSuccessView().visibility = viewStatus
        if(viewStatus == View.VISIBLE){
            hideError()
            hideProgress()
        }
    }
}