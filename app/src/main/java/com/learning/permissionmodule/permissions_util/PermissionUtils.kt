package com.learning.permissionmodule.permissions_util

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.learning.permissionmodule.MainActivity


object PermissionUtils {

    fun getPermission(context: Context) {

        // below line is use to request permission in the current activity.
        // this method is use to handle error in runtime permissions

        Dexter.withContext(context).withPermissions(
            Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                // this method is called when all permissions are granted
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    // do your work now
                    Toast.makeText(context, "All the permissions are granted..", Toast.LENGTH_SHORT)
                        .show()
                }
                // check for permanent denial of any permission
                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {
                    // permission is denied permanently, we will show user a dialog message.
                    showSettingsDialog(context as MainActivity)
//                            Toast.makeText(context, "Please Go To Setting for Permission", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                list: List<PermissionRequest>,
                permissionToken: PermissionToken
            ) {
                // this method is called when user grants some permission and denies some of them.
                permissionToken.continuePermissionRequest()
            }
        }).withErrorListener {
            // we are displaying a toast message for error message.
            Toast.makeText(context, "Error occurred! ", Toast.LENGTH_SHORT).show()
        }
            // below line is use to run the permissions on same thread and to check the permissions
            .onSameThread().check()

    }


    // which is use to display a dialogue message.
    private fun showSettingsDialog(context: Context) {
        // we are displaying an alert dialog for permissions
        val builder = AlertDialog.Builder(context)

        // below line is the title for our alert dialog.
        builder.setTitle("Need Permissions")

        // below line is our message for our dialog
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setCancelable(false)
        builder.setPositiveButton("GOTO SETTINGS") { dialog, _ ->
            // this method is called on click on positive button and on clicking shit button
            // we are redirecting our user from our app to the settings page of our app.
            dialog.dismiss()
            // below is the intent from which we are redirecting our user.
            context.startActivity(Intent(Settings.ACTION_SETTINGS))
//            context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS))
//            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)

//            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//            val uri = Uri.fromParts("package", "com.learning.permissionmodule", null)
//            intent.data = uri
//            context.startActivity(intent)

        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            // this method is called when user click on negative button.
            dialog.dismiss()
        }
        // below line is used to display our dialog
        builder.show()
    }

//    private fun startActivityForResult() {
//
//    }

}