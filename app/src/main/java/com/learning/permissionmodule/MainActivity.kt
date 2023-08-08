package com.learning.permissionmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.PermissionRequest
import android.widget.Button
import com.learning.permissionmodule.permissions_util.PermissionUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val requestPermissionsBtn = findViewById<Button>(R.id.idBtnRequestPermission)
        requestPermissionsBtn.setOnClickListener {
            // inside on click listener calling method to request permission
            PermissionUtils.getPermission(this@MainActivity)
        }

    }

}