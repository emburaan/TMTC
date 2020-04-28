package com.dpoints.dpointsmerchant.view.commons.base

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tmtc.R
import com.tmtc.view.common.dialog.ResponseDialog
import com.wang.avi.AVLoadingIndicatorView


abstract class BaseFragment : Fragment() {

    protected var baseActivity: BaseActivity? = null
    abstract val layout: Int @LayoutRes get
    lateinit var progressDialog: Dialog
    abstract fun init(view: View)
 var myView :View ?= null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        when (context) {
            is BaseActivity -> this.baseActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView = view
        init(view)
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }
    open fun showProgress(context:Context) {
        progressDialog = Dialog(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_demo, null)
        progressDialog.setContentView(dialogView)

        val progressbar =  dialogView.findViewById<AVLoadingIndicatorView>(R.id.progress)
        progressbar.smoothToShow()
        progressDialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        progressDialog.setCancelable(false)
        progressDialog.show()    }

    open fun hideProgress() {
        progressDialog.dismiss()
    }


    fun onFailure(message: CharSequence, view: View = myView!!.findViewById(R.id.root_view)) {
        Log.e("FALIURE", "ON")
        onFailure(view, message)
    }

    fun onSuccess(message: CharSequence, view: View = myView!!.findViewById(R.id.root_view)) {
        Log.e("SUCCESS", "ON")
        onSuccess(view, message)
    }

    @JvmOverloads
    open fun onError(
        message: String,
        clickListener: () -> Unit = {},
        cancellable: Boolean = false,
        @DrawableRes icon: Int = R.drawable.ic_error,
        btnText: String = getString(R.string.okay),
        view: View = myView!!.findViewById(R.id.root_view)
    ) {
        Log.e("Error", "ON")

        ResponseDialog.Builder()
            .message(message)
            .attachActionBlock(clickListener)
            .icon(icon)
            .isCancellable(cancellable)
            .btnText(btnText)
            .build()
            .show(activity!!.supportFragmentManager, "")
    }

    open fun onFailure(view: View, message: CharSequence) {
        Log.e("FALIURE", "ON")
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.red_dark))
        val textView =
            snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(context!!, R.color.white))
        snackBar.show()
    }

    open fun onSuccess(view: View, message: CharSequence) {
        Log.e("SUCCESS", "ON")
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.blue_black_light))
        val textView =
            snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(context!!, R.color.white))
        snackBar.show()
    }
}