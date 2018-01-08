package com.github.lemarck.smartclicker

import java.awt.Robot
import java.awt.event.KeyEvent


class ClickListener {
    private val robot = Robot()

    private fun getKey(code: Int): Int? {
        return when (code) {
            48 -> KeyEvent.VK_ENTER
            49 -> KeyEvent.VK_UP
            50 -> KeyEvent.VK_RIGHT
            51 -> KeyEvent.VK_DOWN
            52 -> KeyEvent.VK_LEFT
            else -> null
        }
    }

    fun onClick(code: Int): Boolean {
        val key = getKey(code)
        if (key !== null) {
            robot.keyPress(key)
            robot.keyRelease(key)
            return true
        }
        return false
    }
}
