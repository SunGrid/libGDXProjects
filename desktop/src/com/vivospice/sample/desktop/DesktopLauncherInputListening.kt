package com.vivospice.sample.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.vivospice.sample.GdxSampleGame
import com.vivospice.sample.InputListeningSample
import com.vivospice.sample.InputPollingSample


fun main (args: Array<String>) {

        val config = LwjglApplicationConfiguration()
        config.width = 1080
        config.height = 720

        LwjglApplication( InputListeningSample(), config)
    }
