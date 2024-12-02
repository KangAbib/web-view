package balloh.khabib.ahmad.pml13

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import balloh.khabib.ahmad.pml13.databinding.FragmentWebviewBinding

class FragmentWebView : Fragment() {
    lateinit var thisParent: MainActivity
    lateinit var wbSet: WebSettings
    lateinit var b: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        thisParent = activity as MainActivity
        b = FragmentWebviewBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onStart() {
        super.onStart()
        webSettings()
        b.webV.loadUrl(GlobalVariables.url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun webSettings() {
        wbSet = b.webV.settings
        wbSet.domStorageEnabled = true
        wbSet.displayZoomControls = false
        wbSet.useWideViewPort = true
        wbSet.javaScriptEnabled = true
        wbSet.savePassword = true
        wbSet.getJavaScriptEnabled()
        wbSet.cacheMode = WebSettings.LOAD_DEFAULT
        wbSet.setGeolocationEnabled(true)
        wbSet.allowContentAccess = true
        wbSet.loadsImagesAutomatically = true
        b.webV.webViewClient = WebViewClient()
    }
}