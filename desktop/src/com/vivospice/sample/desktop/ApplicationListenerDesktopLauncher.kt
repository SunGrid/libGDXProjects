package com.vivospice.sample.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.vivospice.sample.samples.ApplicationListenerSample


fun main (args: Array<String>) {

        LwjglApplication(ApplicationListenerSample(), LwjglApplicationConfiguration())
    }
