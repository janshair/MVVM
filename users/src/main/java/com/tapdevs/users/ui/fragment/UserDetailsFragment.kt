package com.tapdevs.users.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tapdevs.core.utils.JavaScriptInterface
import com.tapdevs.users.R
import com.tapdevs.users.databinding.FragmentUserDetailsBinding

private const val EXTRA_PROFILE_URL = "EXTRA_PROFILE_URL"
private const val TAG = "UserDetailsFragment"
class UserDetailsFragment: Fragment() {

    /**
     * Progress and error views can be added to make a better UI. See UsersFragment for implementation
     */
    
    private lateinit var fragmentUserDetailsBinding: FragmentUserDetailsBinding

    private var profileUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileUrl = arguments?.getString(EXTRA_PROFILE_URL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentUserDetailsBinding =  FragmentUserDetailsBinding.inflate(inflater, container, false)
        return fragmentUserDetailsBinding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebView()
        loadUrl()
    }

    private fun loadUrl() {
        profileUrl?.let {
            fragmentUserDetailsBinding.webViewUserDetails.loadUrl(it)
        } ?: Toast.makeText(activity, getString(R.string.error_loading_data), Toast.LENGTH_SHORT).show()
    }

    private fun setUpWebView() {
        val userDetailsWebViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.d(TAG, "Finished Loading - $url",)
            }
        }

        fragmentUserDetailsBinding.webViewUserDetails.apply {
            settings.javaScriptEnabled = true
            webViewClient = userDetailsWebViewClient
            val javascriptInterface = JavaScriptInterface()
            addJavascriptInterface(javascriptInterface, getString(R.string.details_page))
        }

    }

}