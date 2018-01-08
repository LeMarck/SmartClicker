package com.github.lemarck.smartclicker

import javax.bluetooth.DiscoveryAgent
import javax.bluetooth.LocalDevice
import javax.microedition.io.Connector
import javax.microedition.io.StreamConnectionNotifier


fun main(args: Array<String>) {
    BluetoothServer.start()
}

object BluetoothServer {
    private val localDevice = LocalDevice.getLocalDevice()
    private val clickListener = ClickListener()
    private val uuid = "0000110100001000800000805F9B34FB"

    fun start() {
        localDevice.discoverable = DiscoveryAgent.GIAC
        val connectionNotifier = Connector.open("btspp://localhost:$uuid") as StreamConnectionNotifier

        while (true) {
            val connection = connectionNotifier.acceptAndOpen()
            val reader = connection.openInputStream()

            do {
                val res = clickListener.onClick(reader.read())
            } while (res)
        }
    }
}
