package com.vivospice.sample.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.badlogic.gdx.utils.reflect.ClassReflection
import com.vivospice.sample.common.SampleFactory
import com.vivospice.sample.common.SampleInfos
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class GdxSamplerLauncher : JFrame() {

    private val windowWidth = 1280
    private val windowHeight = 720
    private val windowSize = Dimension(windowWidth, windowHeight)
    private val cellWidth = 200
    private val canvasWidth = windowWidth - cellWidth

                                //for Swing apps
    private var lwjglAWTCanvas: LwjglAWTCanvas? = null
    private lateinit var sampleList: JList<String>

    init{
        title = GdxSamplerLauncher::class.java.simpleName
        minimumSize = windowSize
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        createControlPanel()

        addWindowListener(object : WindowAdapter(){
            override fun windowClosing(p0: WindowEvent?) {
                println("windowClosing")
                lwjglAWTCanvas?.stop()
            }
        })

       // launchSample("com.vivospice.sample.samples.InputPollingSample")

        //tell window/jframe to resize and layout components
        pack()
        isVisible = true
    }

    private fun createControlPanel(){
        val controlPanel = JPanel(GridBagLayout())
        val c = GridBagConstraints()

        //scrollpane
        c.apply {
            gridx = 0 // column inside the gridBagLayoug where we want to position the scrollpane
            gridy = 0 // row
            fill = GridBagConstraints.VERTICAL // fill vertically
            weighty = 1.0 // fill empty space
        }

        sampleList = JList(SampleInfos.getSampleNames())
        sampleList.fixedCellWidth = cellWidth
        sampleList.selectionMode = ListSelectionModel.SINGLE_SELECTION // one sample in window at a time

        //add double click to launch sample
        sampleList.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(mouseEvent: MouseEvent?) {
                if (mouseEvent?.clickCount == 2) {
                    launchSelectedSample()
                }
            }
        })

        val scrollPane = JScrollPane(sampleList)
        controlPanel.add(scrollPane, c)

        //button
        c.apply {
            gridx = 0 // column
            gridy = 1 // row
            fill = GridBagConstraints.HORIZONTAL
            weighty = 0.0
        }

        val launchButton = JButton("Launch Sample")
        launchButton.addActionListener { launchSelectedSample() }

        controlPanel.add(launchButton, c)

        //add to JFrame, the main window
        contentPane.add(controlPanel, BorderLayout.WEST)
    }

    private fun launchSelectedSample() {
        val sampleName : String? = sampleList.selectedValue

        if( sampleName.isNullOrBlank() ) {
            println("sample name is null or blank, can't launch")
            return
        }

        launchSample(sampleName)
    }

    private fun launchSample(name: String?){
        println("launching name= $name")

        // cleanup before running new sample
        lwjglAWTCanvas?.stop()

        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }

/*        //get class object by name
        val sampleClass = ClassReflection.forName(name)
        // create new instance of sample class
        val sample = ClassReflection.newInstance(sampleClass) as ApplicationListener*/

        if(!name.isNullOrBlank()) {
            val sample = SampleFactory.newSample(name!!)
            lwjglAWTCanvas = LwjglAWTCanvas(sample)
            lwjglAWTCanvas?.canvas?.size = Dimension(canvasWidth, windowHeight)
            contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)
        }
        pack() //must call pack method to resize and redraw layout
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