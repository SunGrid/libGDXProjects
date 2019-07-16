package com.vivospice.sample

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GdxSampleGame : ApplicationAdapter() {
    //SpriteBatch() cant' be used until application or framework boots itself
    // using lateinit requires var. val won't work.
    lateinit var batch : SpriteBatch // = SpriteBatch()
    lateinit var img: Texture // = Texture("badlogic.jpg")

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render(){
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()
        batch.draw(img, 0.0f, 0.0f)
        batch.end()

    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }

}