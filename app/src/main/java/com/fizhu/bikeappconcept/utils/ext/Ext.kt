package com.fizhu.bikeappconcept.utils.ext

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.fizhu.bikeappconcept.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by dhruv on 06,12,2021
 */

private var exitTime: Long = 0

/** The loading progress dialog object */
var progressDialog: ProgressDialog? = null

fun Context.toast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.hideKeyboard(windowToken: IBinder) {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun String.getRequest(): RequestBody {
    val mediaType = "application/json".toMediaTypeOrNull()
    return toRequestBody(mediaType)
}

fun startActivityWithFade(activity: Activity,clazz: Class<*>) {
    activity.startActivity(Intent(activity, clazz))
    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun doExitApp(activity: Activity) {
    if (System.currentTimeMillis() - exitTime > 2000) {
        Toast.makeText(activity, R.string.label_do_exit, Toast.LENGTH_SHORT).show()
        exitTime = System.currentTimeMillis()
    } else {
        activity.finish()
    }
}

/**
 * Simplify logging with timber, and make it into function !
 * @param pesan is the logging message
 * @param context is only to get the name of which context that provide the log message
 */
fun logi(pesan: String?) = Timber.tag("BIKE APP-LOG D").d( "Message : $pesan")

fun logi(context: Context,pesan: String?) = Timber.tag("LOG D ${context.javaClass.simpleName}").d( "Message : $pesan")

fun loge(pesan: String?) = Timber.tag("BIKE APP-LOG E").e( "Message : $pesan")

fun loge(context: Context,pesan: String?) = Timber.tag("LOG E ${context.javaClass.simpleName}").e( "Message : $pesan")

fun logw(pesan: String?) = Timber.tag("BIKE APP-LOG W").e( "Message : $pesan")

/**
 * Shows a loading progress dialog.
 * @param context the context
 * @param message the dialog message string
 * @param onBackPressListener the back button press listener when loading is shown
 */
fun showProgressDialog(context: Context, message: String) {
    dismissProgressDialog()
    progressDialog = ProgressDialog(context)
    progressDialog!!.setMessage(message)
    progressDialog!!.setCancelable(false)
    if (context is Activity && !context.isFinishing) progressDialog!!.show()
}

/** Hides the currently shown loading progress dialog */
fun dismissProgressDialog() {
    if (progressDialog != null && progressDialog!!.isShowing) {
        progressDialog!!.dismiss()
        progressDialog = null
    }
}

fun Context.color(resource: Int): Int {
    return ContextCompat.getColor(this, resource)
}

fun Fragment.color(resource: Int): Int {
    context?.let {
        return ContextCompat.getColor(it, resource)
    }
    return 0
}
