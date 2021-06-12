package com.fizhu.bikeappconcept.utils.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.bikeappconcept.R

class Utils {
    companion object{
        private var progressDialog: Dialog? = null
        const val IMAGE_BASE = "https://staging.grubbrr.com"

        @JvmStatic
        fun showProgressDialogApp(mContext: Context, message: String) {
            if (!(mContext as AppCompatActivity).isFinishing) {
                if (progressDialog == null || !progressDialog!!.isShowing) {
                    progressDialog = Dialog(mContext)
                    progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    progressDialog!!.setContentView(R.layout.dialog_fetch_detail_progress)
                    val tvMessage = progressDialog!!.findViewById(R.id.tvProgressTitle) as TextView
                    tvMessage.text = message

                    progressDialog!!.setCancelable(false)
                    if (!mContext.isFinishing)
                        progressDialog!!.show()
                }
            }
        }

        @JvmStatic
        fun hideProgressDialogApp() {
            try {
                if (progressDialog != null && progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                    progressDialog = null

                }
            } catch (throwable: Throwable) {

            } finally {
                progressDialog = null
            }
        }
    }




}