package com.github.lemarck.smartclicker

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: DeviceListAdapter

    private val REQUEST_ENABLE_BT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled) {
            startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT)
        }
        PermissionManager(this)
                .requestPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DeviceListAdapter(this)
        devices.layoutManager = LinearLayoutManager(this)
        devices.adapter = adapter
    }

    override fun onResume() {
        BluetoothAdapter
                .getDefaultAdapter()
                .bondedDevices
                .forEach { adapter.addDevice(it) }
        super.onResume()
    }

    override fun onPause() {
        adapter.clearDevices()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((requestCode == REQUEST_ENABLE_BT) and (resultCode != Activity.RESULT_OK)) {
            // TODO: Обработка отказа включить Bluetooth
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
