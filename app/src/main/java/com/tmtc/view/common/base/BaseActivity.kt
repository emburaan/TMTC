package com.dpoints.dpointsmerchant.view.commons.base

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dpoints.dpointsmerchant.datasource.remote.ApiClient
import com.dpoints.dpointsmerchant.preferences.UserPreferences
import com.dpoints.dpointsmerchant.utilities.LoginType
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.tmtc.R
import com.tmtc.view.common.dialog.ResponseDialog
import com.tmtc.view.modules.dashboard.DashBoardActivity
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.dialog_demo.*


abstract class BaseActivity : AppCompatActivity(){

    abstract val layout: Int @LayoutRes get
    abstract fun init()
    lateinit var progressDialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setupFocusOutside(findViewById(android.R.id.content))
        init()

    }

    open fun showProgress(context:Context) {
        progressDialog = Dialog(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_demo, null)
        progressDialog.setContentView(dialogView)

        val progressbar =  dialogView.findViewById<AVLoadingIndicatorView>(R.id.progress)
        progressbar.smoothToShow()
        progressDialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        progressDialog.setCancelable(false)
        progressDialog.show()


           /* progressDialog =
                ProgressDialog.show(context, "Please wait...", "Processing Data...", false, false)
*/
    }

     fun hideProgress() {
        Log.e("initialized", "" + ::progressDialog.isInitialized)
            when {
                (::progressDialog.isInitialized) -> {
                    progressDialog.dismiss()

                }
            }
             }


    fun onFailure(message: CharSequence, view: View = findViewById(R.id.root_view)) {
        Log.e("FALIURE", "ON")
        onFailure(view, message)
    }

    fun onSuccess(message: CharSequence, view: View = findViewById(R.id.root_view)) {
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
        view: View = findViewById(R.id.root_view)
    ) {
        Log.e("Error", "ON")

        ResponseDialog.Builder()
            .message(message)
            .attachActionBlock(clickListener)
            .icon(icon)
            .isCancellable(cancellable)
            .btnText(btnText)
            .build()
            .show(supportFragmentManager, "")
    }

    open fun onFailure(view: View, message: CharSequence) {
        Log.e("FALIURE", "ON")
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red_dark))
        val textView =
            snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackBar.show()
    }

    open fun onSuccess(view: View, message: CharSequence) {
        Log.e("SUCCESS", "ON")
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_black_light))
        val textView =
            snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackBar.show()
    }

    open fun bottomnavigatio(view: View) {

    }

    /*fun logout() {
        ApiClient.clearInstance()
        FirebaseAuth.getInstance().signOut()
        LoginManager.getInstance().logOut()
        FirebaseAuth.getInstance().signOut()
        UserPreferences.instance.clear(this)
        startLoginActivity()
    }*/

    fun hideKeyboard() {
        val currentView = this.currentFocus
        if (currentView != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentView.windowToken, 0)
        }
    }

    open fun isNetworkConnected(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return manager?.activeNetworkInfo?.isConnected ?: false
    }

   /* open fun startLoginActivity() {
        val intent = Intent(Intent(this, Login::class.java))
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }*/

    fun startDashboardActivity() {
        startActivity(Intent(this, DashBoardActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    inline fun <reified T : Activity> startActivity(bundle: Bundle? = null) {
        if (bundle != null) {
            return startActivity(Intent(this, T::class.java).putExtras(bundle))
        }

        startActivity(Intent(this, T::class.java))
    }

    fun clearNotification() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.cancelAll()
    }

    private fun setupFocusOutside(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupFocusOutside(innerView)
            }
        }
    }

    fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode,
                    PLAY_SERVICES_RESOLUTION_REQUEST
                )
            }

            return false
        }

        return true
    }

    fun isGpsEnabled(): Boolean {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return manager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
    }

    companion object {
        private const val PLAY_SERVICES_RESOLUTION_REQUEST = 876
    }

}