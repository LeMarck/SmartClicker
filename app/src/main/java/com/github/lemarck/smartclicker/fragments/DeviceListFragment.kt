package com.github.lemarck.smartclicker.fragments

import android.app.Fragment
import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.lemarck.smartclicker.R
import com.github.lemarck.smartclicker.adapters.DeviceListAdapter
import kotlinx.android.synthetic.main.fragment_device_list.view.*


class DeviceListFragment : Fragment() {
    private lateinit var adapter: DeviceListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_device_list, container, false)

        adapter = DeviceListAdapter(activity)
        root.devices.layoutManager = LinearLayoutManager(activity)
        root.devices.adapter = adapter
        return root
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
}
