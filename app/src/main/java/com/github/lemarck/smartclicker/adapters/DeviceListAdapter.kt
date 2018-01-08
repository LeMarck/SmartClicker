package com.github.lemarck.smartclicker.adapters

import android.app.Activity
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.lemarck.smartclicker.R
import com.github.lemarck.smartclicker.fragments.ClickerFragment
import kotlinx.android.synthetic.main.device_list_item.view.*


class DeviceListAdapter(private val activity: Activity) : RecyclerView.Adapter<DeviceListAdapter.ViewHolder>() {
    private val devices = arrayListOf<BluetoothDevice>()
    private val types = arrayOf(
            BluetoothClass.Device.COMPUTER_LAPTOP,
            BluetoothClass.Device.COMPUTER_DESKTOP
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.device_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = devices[position].name
        holder.address.text = devices[position].address
        if (devices[position].bluetoothClass.deviceClass == BluetoothClass.Device.COMPUTER_LAPTOP) {
            holder.icon.setImageResource(R.drawable.ic_laptop)
        } else {
            holder.icon.setImageResource(R.drawable.ic_desktop)
        }
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    fun addDevice(device: BluetoothDevice) {
        if ((devices.contains(device)) or (!types.contains(device.bluetoothClass.deviceClass)) ) {
            return
        }
        devices.add(device)
        this.notifyDataSetChanged()
    }

    fun clearDevices() {
        devices.clear()
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.device_name
        val address: TextView = itemView.device_address
        val icon: ImageView = itemView.device_icon
        init {
            itemView.setOnClickListener {
                try {
                    activity.fragmentManager
                            .beginTransaction()
                            .replace(R.id.container, ClickerFragment.newInstance(address.text.toString()))
                            .commit()
                }
                catch (e: Error) {
                    // TODO: Обработка ошибки
                }
            }
        }
    }
}
