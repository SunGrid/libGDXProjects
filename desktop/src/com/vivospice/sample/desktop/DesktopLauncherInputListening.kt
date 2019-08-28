package com.vivospice.sample.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.vivospice.sample.samples.InputListeningSample


fun main (args: Array<String>) {

        val config = LwjglApplicationConfiguration()
        config.width = 1080
        config.height = 720

        LwjglApplication(InputListeningSample(), config)
    }
