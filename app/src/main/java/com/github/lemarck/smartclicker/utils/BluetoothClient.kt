package com.github.lemarck.smartclicker.utils

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import java.io.OutputStream
import java.util.*


class BluetoothClient {
    private lateinit var socket: BluetoothSocket
    private lateinit var writer: OutputStream
    private val uuid = "00001101-0000-1000-8000-00805F9B34FB"

    fun connection(address: String) {
        val bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address)
        socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        socket.connect()
        writer = socket.outputStream
    }

    fun close() {
        socket.close()
    }

    fun send(code: Int) {
        try {
            writer.write(code.toString().toByteArray())
        }
        catch (e: Error) {
            // TODO обработка ошибки
        }
    }
}