package com.vivospice.sample.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.badlogic.gdx.utils.reflect.ClassReflection
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

class GdxSamplerLauncher : JFrame() {

    private val windowSize = Dimension(1280, 720)

                                //for Swing apps
    private var lwjglAWTCanvas: LwjglAWTCanvas? = null

    init{
        title = GdxSamplerLauncher::class.java.simpleName
        minimumSize = windowSize
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        launchSample("com.vivospice.sample.InputPollingSample")

        //tell window/jframe to resize and layout components
        pack()
        isVisible = true
    }

    private fun launchSample(name: String){
        println("launching name= $name")

        // cleanup before running new sample
        lwjglAWTCanvas?.stop()

        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }

        //get class object by name
        val sampleClass = ClassReflection.forName(name)

        // create new instance of sample class
        val sample = ClassReflection.newInstance(sampleClass) as ApplicationListener

        lwjglAWTCanvas = LwjglAWTCanvas(sample)
        lwjglAWTCanvas?.canvas?.size = windowSize
        contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)
    }
}

// == main ==
fun main() {
/* SwingUtilities.invokeLater(object : Runnable {
     override fun run() {
         GdxSamplerLauncher()
     }
 })
 */
    SwingUtilities.invokeLater { GdxSamplerLauncher() }
}