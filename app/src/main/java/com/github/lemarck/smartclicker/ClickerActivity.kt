package com.github.lemarck.smartclicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class ClickerActivity : AppCompatActivity() {
    private lateinit var address: String

    private val bluetoothClient = BluetoothClient()
    private val buttons = arrayOf(
            R.id.navigator_ok,
            R.id.navigator_arrow_up,
            R.id.navigator_arrow_right,
            R.id.navigator_arrow_down,
            R.id.navigator_arrow_left
    )

    companion object {
        val ADDRESS = "${this::class.java.canonicalName}_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicker)

        buttons.forEachIndexed { index, button ->
            findViewById<Button>(button).setOnClickListener {
                bluetoothClient.send(index)
            }
        }
    }

    override fun onResume() {
        address = intent.extras.getString(ADDRESS) ?: address
        bluetoothClient.connection(address)
        super.onResume()
    }

    override fun onPause() {
        bluetoothClient.close()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ADDRESS, address)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        address = savedInstanceState.getString(ADDRESS)
        super.onRestoreInstanceState(savedInstanceState)
    }
}
