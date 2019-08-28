package com.vivospice.sample.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.vivospice.sample.common.SampleBase
import com.vivospice.sample.utils.logger

class ApplicationListenerSample : SampleBase() {

    companion object {
        //call properties from companion object directly by using the class
        //like static in Java
        @JvmStatic
        private val log = logger<ApplicationListenerSample>()
        //private val log = logger(ApplicationListenerSample::class.java)
        //private val log = Logger(ApplicationListenerSample::class.java.simpleName, Logger.DEBUG)


    }


    private var renderInterrupted = true

    override fun create() {
        //used to initialize game and load resources
        //Gdx.app.debug("ApplicationListenerSample", "create()")
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create()")

    }

    override fun resize(width: Int, height: Int) {
        // used to handle setting a new screen size
        log.debug("resize()")
    }


    override fun render() {
        //used to update and render the game elements called 60 times per second
        if (renderInterrupted) { //use boolean to check and so it doesn't debug every 60 sec
            log.debug("render()")
            renderInterrupted = false
        }
    }

    override fun pause() {
        //used to save game state when it loses focus, which does not involve the actual
        //game play being paused unless the developer wants to pause
        log.debug("pause()")
        renderInterrupted = true
    }

    override fun resume() {
        //used to handle the game coming back from being paused and restores game state
        log.debug("resume()")
        renderInterrupted = true
    }

    override fun dispose() {
        //used to free resources and cleanup
        log.debug("dispose")
    }


}