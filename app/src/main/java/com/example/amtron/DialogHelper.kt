package com.example.amtron

import android.app.Activity
import android.app.Dialog

import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog





class DialogHelper {


     private var loadingDialog: Dialog?=null

    lateinit var noInternetDialog : Dialog

    lateinit var messageDialog : AlertDialog


    fun showLoadingDialog(activity: Activity) {
        loadingDialog = Dialog(activity)
        loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog!!.setCancelable(false)
        loadingDialog!!.setContentView(R.layout.loading_view)
        loadingDialog!!.show()

    }

    fun cancelLoadingDialog() {
        loadingDialog?.dismiss()
    }

    fun showMessage(activity: Activity, message : String){
        val builder = AlertDialog.Builder(activity)

        builder.setTitle("Error")
        //set message for alert dialog
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
//        builder.setPositiveButton("Yes"){dialogInterface, which ->
//            Toast.makeText(activity,"clicked yes",Toast.LENGTH_LONG).show()
//        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            messageDialog.dismiss()
//            Toast.makeText(activity,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
        }
        //performing negative action
//        builder.setNegativeButton("No"){dialogInterface, which ->
//            Toast.makeText(activity,"clicked No",Toast.LENGTH_LONG).show()
//        }
        // Create the AlertDialog
        messageDialog = builder.create()
        // Set other dialog properties
        messageDialog.setCancelable(false)
        messageDialog.show()

    }


    fun showInternetDialog(activity: Activity) {
        noInternetDialog = Dialog(activity)
        noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        noInternetDialog.setCancelable(true)
        noInternetDialog.setContentView(R.layout.no_internet_dialog)
        noInternetDialog.show()

    }


    fun cancelInternetDialog() {
        if(noInternetDialog!=null)
            noInternetDialog.dismiss()
    }



}