package com.vivospice.sample.samples

//import com.badlogic.gdx.utils.Array as GdxArray
import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.vivospice.sample.common.SampleBase
import com.vivospice.sample.utils.GdxArray
import com.vivospice.sample.utils.clearScreen
import com.vivospice.sample.utils.logger
import com.vivospice.sample.utils.toInternalFile


// typealias can be used in place of "as" after the import. This will best done in a separate util file.
//typealias GdxArray<T> = com.badlogic.gdx.utils.Array<T>


class InputListeningSample : SampleBase() {

    companion object{
        @JvmStatic
        private val log = logger<InputListeningSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch //used to draw something on screen.
    lateinit var font: BitmapFont  //load it from assets folder

    private val maxMessageCount = 15
    private val messages = GdxArray<String>()

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        //instanceiate the feilds
        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()// public class SpriteBatch implements Batch ---- public interface Batch extends Disposable
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile()) // to get the file use Gdx files module

        Gdx.input.inputProcessor = this //sets to this being this class InputListeningSample
                    //inputProcessor is a setter from Input interface with method setInputProcessor (InputProcessor processor);
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true) // need to tell viewport about camera and screen change
    }

    override fun render() {
        //clear screen first//  every frame needs clearing and drawing.
        clearScreen()

        //batch to draw something but must set projection matrix on the batch. Telling batch about camera position and
        //zoom value and the camera to use.
        batch.projectionMatrix = camera.combined
        batch.begin() //drawing between batch.begin() and batch.end()

        draw()

        batch.end()
    }

    private fun draw(){
        for (i in 0 until messages.size) {
            font.draw(batch, messages[i],
                    20f,
                    720 - 40f * i
            )
        }
    }

    private fun addMessage(message: String){
        messages.add(message)

        if (messages.size > maxMessageCount){
            messages.removeIndex(0)
        }
    }

    //all methods that extends Disposable must be cleaned up resources/memory that these classes take.
    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val message = "touchUp screenX= $screenX, screenY= $screenY"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val message = "mouseMoved screenX= $screenX, screenY= $screenY"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        val message = "keyTyped characters= $character"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun scrolled(amount: Int): Boolean {
        val message = "scrolled amount= $amount"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        val message = "keyUp keycode= $keycode"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val message = "touchDragged  screenX= $screenX, screenY= $screenY"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        val message = "keyDown keycode= $keycode"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val message = "touchDown screenX= $screenX, screenY= $screenY"
        log.debug(message)
        addMessage(message)
        return true
    }
}