package com.github.lemarck.smartclicker.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.github.lemarck.smartclicker.R
import com.github.lemarck.smartclicker.utils.BluetoothClient


class ClickerFragment : Fragment() {
    private lateinit var address: String

    private val bluetoothClient = BluetoothClient()
    private val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    private val buttons = arrayOf(
            R.id.navigator_ok,
            R.id.navigator_arrow_up,
            R.id.navigator_arrow_right,
            R.id.navigator_arrow_down,
            R.id.navigator_arrow_left
    )

    companion object {
        private val ADDRESS = "${this::class.java.canonicalName}_address"

        fun newInstance(address: String): ClickerFragment {
            val args = Bundle()
            args.putString(ADDRESS, address)
            val fragment = ClickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_clicker, container, false)
        buttons.forEachIndexed { index, i ->
            root.findViewById<Button>(i).setOnClickListener {
                bluetoothClient.send(index)
            }
        }
        return root
    }

    override fun onResume() {
        address = arguments.getString(ADDRESS) ?: address
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState !== null) {
            address = savedInstanceState.getString(ADDRESS)
        }
        super.onViewStateRestored(savedInstanceState)
    }
}
