package com.github.lemarck.smartclicker

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat


class PermissionManager(private val context: Activity) {
    private val PERMISSION_REQUEST_CODE = 999

    fun requestPermissions(vararg permissions: String) {
        val permits = permissions.filter { !checkPermission(it) }
        if (permits.isNotEmpty()) {
            ActivityCompat
                    .requestPermissions(context, permits.toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }

    fun checkPermission(permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }
}
