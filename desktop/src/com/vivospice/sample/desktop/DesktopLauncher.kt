package com.vivospice.sample.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.vivospice.sample.GdxSampleGame


    fun main (args: Array<String>) {

        LwjglApplication( GdxSampleGame(), LwjglApplicationConfiguration())
    }
