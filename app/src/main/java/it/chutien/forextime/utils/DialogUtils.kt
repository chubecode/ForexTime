package it.chutien.forextime.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import it.chutien.forextime.R

object DialogUtils {


    @SuppressLint("SetJavaScriptEnabled")
    fun showChart(context: Context?, symbol:String) {
        if (context == null) return
        val dialogChart =  AlertDialog.Builder(context)
            .setView(R.layout.dialog_chart)
            .create().apply {
                setCancelable(true)
                setCanceledOnTouchOutside(true)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

        dialogChart.show()
        val webChart = dialogChart.findViewById<WebView>(R.id.web_chart)
        if (webChart != null) {
            webChart.webChromeClient = WebChromeClient()
            if(Build.VERSION.SDK_INT < 19){
                webChart.setLayerType(View.LAYER_TYPE_HARDWARE,null)
            }else{
                webChart.setLayerType(View.LAYER_TYPE_SOFTWARE,null)
            }
            webChart.settings.javaScriptEnabled = true
            webChart.loadUrl("https://heyktortaone.herokuapp.com/chart?symbol=${symbol}")
        }
    }

    fun createLoadingDialog(
        context: Context?, cancelable: Boolean = false,
        canceledOnTouchOutside: Boolean = false
    ): AlertDialog? {
        if (context == null) return null
        return AlertDialog.Builder(context)
            .setView(R.layout.layout_dialog_process)
            .create().apply {
                setCancelable(cancelable)
                setCanceledOnTouchOutside(canceledOnTouchOutside)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    fun showLoadingDialog(
        context: Context?, cancelable: Boolean = false,
        canceledOnTouchOutside: Boolean = false
    ): AlertDialog? {
        if (context == null) return null
        val dialog: AlertDialog? = createLoadingDialog(context, cancelable)
        dialog?.show()
        return dialog
    }

    fun showMessage(
        context: Context?, title: String? = null, message: String? = null,
        textPositive: String? = null, positiveListener: (() -> Unit)? = null,
        textNegative: String? = null, negativeListener: (() -> Unit)? = null,
        cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false
    ): AlertDialog? {
        if (context == null) return null
        return AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(textPositive) { dialog, which ->
                positiveListener?.invoke()
            }
            setNegativeButton(textNegative) { dialog, which ->
                negativeListener?.invoke()
            }
            setCancelable(cancelable)

        }.create().apply {
            setCanceledOnTouchOutside(canceledOnTouchOutside)
            show()
        }
    }

    fun showMessage(
        context: Context?, title: Int? = null, message: Int? = null,
        textPositive: Int? = null, positiveListener: (() -> Unit)? = null,
        textNegative: Int? = null, negativeListener: (() -> Unit)? = null,
        cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false
    ): AlertDialog? {
        if (context == null) return null
        return AlertDialog.Builder(context).apply {
            if (title != null) setTitle(title)
            if (message != null) setMessage(message)
            if (textPositive != null) {
                setPositiveButton(textPositive) { dialog, which ->
                    positiveListener?.invoke()
                }
            }
            if (textNegative != null) {
                setNegativeButton(textNegative) { dialog, which ->
                    negativeListener?.invoke()
                }
            }
            setCancelable(cancelable)
        }.create().apply {
            setCanceledOnTouchOutside(canceledOnTouchOutside)
            show()
        }
    }
}