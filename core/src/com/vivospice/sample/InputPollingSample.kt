package com.vivospice.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeType
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.vivospice.sample.utils.logger

class InputPollingSample : ApplicationAdapter() {
    //Polling means it will run every frame.
    //every frame asking libGdx if some key or button is pressed


    companion object {
        @JvmStatic
        private val log = logger<ModuleInfoSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch //used to draw something on sreen.
    lateinit var font: BitmapFont  //load it from assets folder

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        //instanceiate the feilds
        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()// public class SpriteBatch implements Batch ---- public interface Batch extends Disposable
        font = BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt")) // to get the file use Gdx files module
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true) // need to tell viewport about camera and screen change
    }

    override fun render() {
        //clear screen first//  every frame needs clearing and drawing.
        Gdx.gl.glClearColor(0f,0f,0f, 1f) // black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //batch to draw something but must set projection matrix on the batch. Telling batch about camera position and
        //zoom value and the camera to use.
        batch.projectionMatrix = camera.combined
        batch.begin() //drawing between batch.begin() and batch.end()

        draw()

        batch.end()
    }

    private fun draw(){
        // mouse / touch x/y coordinantes
        val mouseX = Gdx.input.x
        val mouseY = Gdx.input.y

        val leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        val rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT)

        font.draw(batch, "Mouse x= $mouseX y= $mouseY", 20f, 720f - 20f ) // 720 is the height/ is the top to button
        // 0,0 is button left.  top left is 720

        val leftPressedString = if (leftPressed) "Left Button Pressed" else "Left Button NOT Pressed"

        font.draw(batch, leftPressedString, 20f, 720f - 50f)

        val rightPressedString = if (rightPressed) "Right Button pressed" else "Right Button NOT Pressed"

        font.draw(batch, rightPressedString, 20f, 720f - 80f)

        //keys
        val wPressed = Gdx.input.isKeyPressed(Input.Keys.W)
        val sPressed = Gdx.input.isKeyPressed(Input.Keys.S)

        font.draw(batch, if (wPressed) "W Pressed" else "W NOT Pressed", 20f, 720f - 110f)

        font.draw(batch, if (sPressed) "S Pressed" else "S NOT Pressed", 20f, 720f - 140f)
    }

    //all methods that extends Disposable must be cleaned up resources/memory that these classes take.
    override fun dispose() {
        batch.dispose()
        font.dispose()
    }


}